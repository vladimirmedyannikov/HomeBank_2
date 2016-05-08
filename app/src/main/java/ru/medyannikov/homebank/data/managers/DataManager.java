package ru.medyannikov.homebank.data.managers;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.activeandroid.query.Select;
import com.birbit.android.jobqueue.JobManager;
import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.medyannikov.homebank.data.job.FetchBillJob;
import ru.medyannikov.homebank.data.job.FetchOperationJob;
import ru.medyannikov.homebank.data.job.InsertBillJob;
import ru.medyannikov.homebank.data.job.InsertOperationJob;
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
    private static DataManager instance;

    @Inject SharedPreferences sharedPreferences;
    @Inject JobManager jobManager;
    @Inject Context context;
    @Inject Bus mBus;
    private static Account userModel;
    private static AccountManager accountManager;
    //private static JobManager jobManager;

    public static DataManager getInstance(){
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }
    //private static Bus mBus;
    private DataManager(){
        AndroidApplication.component().inject(this);
        mBus.register(this);
    }

    public String getToken(){
        return sharedPreferences.getString(ConstantManager.TOKEN_KEY, null);
    }

    public void setToken(String token){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantManager.TOKEN_KEY, token);
        editor.apply();
    }

    public boolean isLogged(){
        return getToken() != null;
    }

    public void logout(){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ConstantManager.TOKEN_KEY, null);
        editor.putString(ConstantManager.EMAIL, null);
        editor.putLong(ConstantManager.USER_ID, 0);
        editor.apply();
    }

    public Bus getBus(){
        return mBus;
    }

    public void getUserInfo(){
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
    public void signIn(String login, String password) {
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
                    //Toast.makeText(AndroidApplication.getContext(), "Логин или пароль неверны!", Toast.LENGTH_LONG).show();
                }
                else if (error.getResponse().getStatus() == 400){
                    //Toast.makeText(AndroidApplication.getContext(), "Введенные данные некорректны!", Toast.LENGTH_LONG).show();
                }
                getBus().post(new LoginFailedEvent("Ошибка авторизации"));
            }
        };
        if (NetworkStatusChecker.isNetworkAvailable(context)) {
            service.signIn(TYPE_AUTH, CLIENT_ID, CLIENT_SECRET, login, password, callback);
        }else{
            getBus().post(new NetworkStatusError());
        }
    }

    public List<Bill> getAllBills(){
        //List<Bill> billList = new ArrayList<>();
        if (NetworkStatusChecker.isNetworkAvailable(context)){

        }
        List<Bill> billList = new Select()
                .from(Bill.class)
                .where("account = ?", DataManager.getInstance().getAccount().getId())
                .execute();
        return billList;
    }


    public void auth(){
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
        if (NetworkStatusChecker.isNetworkAvailable(context)) {
            service.getUserModel(a);
        }
    }

    public void insertBill(Bill bill){
        RestService service = RestFactory.getRestService();
        service.createBill(bill, new Callback<Bill>() {
            @Override
            public void success(Bill bill, Response response) {
                Log.e("lol", response.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("lol", error.toString());
            }
        });
    }

    public void initializeUser(){
        if (isLogged()) {
            String email = sharedPreferences.getString(ConstantManager.EMAIL, "");
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

    public Account getAccount(){
        if (userModel == null) {Toast.makeText(context,"null user",Toast.LENGTH_SHORT).show();}
        return userModel;
    }

    public void setUser(Account user){
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
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putLong(ConstantManager.USER_ID, id).apply();
                editor.putString(ConstantManager.EMAIL, user.getEmail()).apply();
            }
        }
    }

    public static List<Operation> getOperations(Bill bill){
        List<Operation> operationList = new Select()
                .from(Operation.class)
                .where("idBill = ?", bill.getId())
                .execute();
        return operationList;
    }

    public static List<Operation> getAllOperations(){
        List<Operation> operationList = new Select()
                .from(Operation.class)
                .execute();
        return operationList;
    }

    public void fetchBillAsync(){
        jobManager
                .addJobInBackground(new FetchBillJob(DataManager.getInstance().getAccount()));
    }

    public void sendBillAsync(Bill bill){
        jobManager
                .addJobInBackground(new InsertBillJob(bill, DataManager.getInstance().getAccount()));
    }

    public void fetchOperationAsync(Bill bill){
        jobManager
                .addJobInBackground(new FetchOperationJob(bill, DataManager.getInstance().getAccount()));
    }

    public static Bill getBill(long bill) {
        List<Bill> b = new Select()
                .from(Bill.class)
                .where("id = ?", bill)
                .execute();
        return b.get(0);
    }

    public void sendOperationAsync(Operation operation) {
        jobManager
                .addJobInBackground(new InsertOperationJob(operation, DataManager.getInstance().getAccount()));
    }
}
