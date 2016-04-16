package ru.medyannikov.homebank.data.managers;

import android.content.SharedPreferences;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.birbit.android.jobqueue.JobManager;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.medyannikov.homebank.data.job.FetchBillJob;
import ru.medyannikov.homebank.data.job.InsertBillJob;
import ru.medyannikov.homebank.data.managers.events.LoginFailedEvent;
import ru.medyannikov.homebank.data.managers.events.LoginSuccessEvent;
import ru.medyannikov.homebank.data.managers.events.NetworkStatusError;
import ru.medyannikov.homebank.data.network.rest.RestFactory;
import ru.medyannikov.homebank.data.network.rest.RestService;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.data.storage.models.TokenModel;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.ui.AndroidApplication;
import ru.medyannikov.homebank.utils.ConstantManager;
import ru.medyannikov.homebank.utils.NetworkStatusChecker;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class DataManager {
    private static final String CLIENT_ID = "mobileV1";
    private static final String CLIENT_SECRET = "abc123456";
    private static final String TYPE_AUTH = "password";
    private static DataManager instance = new DataManager();
    private static SharedPreferences sharedPreferences;
    private static Account userModel;
    private static AccountManager accountManager;
    private static JobManager jobManager;

    public static DataManager getInstance(){
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }
    private static Bus mBus;
    private DataManager(){
        mBus = new Bus(ThreadEnforcer.ANY);
        mBus.register(this);
        sharedPreferences = AndroidApplication.getSharedPreferences();
        jobManager = AndroidApplication.getJobManager();
    }

    public static String getToken(){
        return sharedPreferences.getString(ConstantManager.TOKEN_KEY, null);
    }

    public static void setToken(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantManager.TOKEN_KEY, token);
        editor.apply();
    }

    public static boolean isLogged(){
        return getToken() != null;
    }

    public static void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantManager.TOKEN_KEY, null);
        editor.putString(ConstantManager.EMAIL, null);
        editor.putLong(ConstantManager.USER_ID, 0);
        editor.apply();
    }

    public static Bus getBus(){
        return mBus;
    }

    public static void getUserInfo(){
        RestService service = RestFactory.getRestService();
        Callback<Account> a = new Callback<Account>() {
            @Override
            public void success(Account user, Response response) {
                if (response.getStatus() == 200){
                    setUser(user);
                } else if (response.getStatus() == 401){
                    //TODO not auth
                }
            }
            @Override
            public void failure(RetrofitError error) {
                //TODO error auth
                error.printStackTrace();
            }
        };
        service.getUserModel(a);
    }

    /**
     * Bus Event LoginSuccessEvent and LoginFailedEvent
     * @param login
     * @param password
     */
    public static void signIn(String login, String password) {
        RestService service = RestFactory.getRestService();
        Callback<TokenModel> callback = new Callback<TokenModel>() {
            @Override
            public void success(TokenModel tokenModel, Response response) {
                if (response.getStatus() == 200){
                    getBus().post(new LoginSuccessEvent(tokenModel));
                }

            }
            @Override
            public void failure(RetrofitError error) {
                error.printStackTrace();
                if (error.getResponse() == null) return;
                if (error.getResponse().getStatus() == 403){
                    Toast.makeText(AndroidApplication.getContext(), "Логин или пароль неверны!", Toast.LENGTH_LONG).show();
                }
                else if (error.getResponse().getStatus() == 400){
                    Toast.makeText(AndroidApplication.getContext(), "Введенные данные некорректны!", Toast.LENGTH_LONG).show();
                }
                getBus().post(new LoginFailedEvent());
            }
        };
        if (NetworkStatusChecker.isNetworkAvailable(AndroidApplication.getContext())) {
            service.signIn(TYPE_AUTH, CLIENT_ID, CLIENT_SECRET, login, password, callback);
        }else{
            getBus().post(new NetworkStatusError());
        }
    }

    public static List<Bill> getAllBills(){
        //List<Bill> billList = new ArrayList<>();
        if (NetworkStatusChecker.isNetworkAvailable(AndroidApplication.getContext())){

        }
        List<Bill> billList = new Select()
                .from(Bill.class)
                .where("account = ?", DataManager.getAccount().getId())
                .execute();
/*        billList.add(new Bill());
        billList.add(new Bill());*/
        return billList;
    }


    public static void auth(){
        RestService service = RestFactory.getRestService();
        Callback<Account> a = new Callback<Account>() {
            @Override
            public void success(Account user, Response response) {
                if (response.getStatus() == 200){
                    setUser(user);
                } else if (response.getStatus() == 401){
                    //TODO not auth
                }
            }
            @Override
            public void failure(RetrofitError error) {
                //TODO error auth
                error.printStackTrace();
            }
        };
        if (NetworkStatusChecker.isNetworkAvailable(AndroidApplication.getContext())) {
            service.getUserModel(a);
        }
    }

    public static void initializeUser(){
        if (isLogged()) {
            String email = AndroidApplication.getSharedPreferences().getString(ConstantManager.EMAIL, "");
            List<Account> userDbList = new Select()
                    .from(Account.class)
                    .where("email = ?", email)
                    .execute();
            if (userDbList.size() > 0) {
                Account userDb = userDbList.get(0);
                userModel = userDb;
            }
        }
    }

    public static Account getAccount(){
        if (userModel == null) {Toast.makeText(AndroidApplication.getContext(),"null user",Toast.LENGTH_SHORT).show();}
        return userModel;
    }

    public static void setUser(Account user){
        if (user != null){
            List<Account> userDbList = new Select()
                    .from(Account.class)
                    .where("email = ?", user.getEmail())
                    .execute();
            if (userDbList.size() > 0){
                Account userDb = userDbList.get(0);
                if (userDb.getStatus() != 1){
                    //TODO update user data
                    if (userDb.getDateUpdate().compareTo(user.getDateUpdate()) >= 0 ){
                        userModel = userDb;
                    }
                    else{
                        //userDb.copyParam(user);
                        //userDb.save();
                    }
                }
                userModel = userDb;
            }
            else
            {
                user.save();
                Long id = user.getId();
                userModel = user;
                SharedPreferences.Editor editor = AndroidApplication.getSharedPreferences().edit();
                editor.putLong(ConstantManager.USER_ID, id).apply();
                editor.putString(ConstantManager.EMAIL, user.getEmail()).apply();
            }
        }
    }

    public static List<Operation> getOperations(Bill bill){
        List<Operation> operationList = new Select()
                .from(Operation.class)
                .where("bill = ?", bill)
                .execute();
        return operationList;
    }

    public static void fetchBillAsync(){
        AndroidApplication.getJobManager()
                .addJobInBackground(new FetchBillJob(DataManager.getAccount()));
    }

    public static void sendBillAsync(Bill bill){
        AndroidApplication.getJobManager()
                .addJobInBackground(new InsertBillJob(bill, DataManager.getAccount()));
    }
}
