package ru.medyannikov.homebank.data.managers;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.events.LoginFailedEvent;
import ru.medyannikov.homebank.data.managers.events.LoginSuccessEvent;
import ru.medyannikov.homebank.data.network.rest.RestFactory;
import ru.medyannikov.homebank.data.network.rest.RestService;
import ru.medyannikov.homebank.data.storage.models.TokenModel;
import ru.medyannikov.homebank.data.storage.models.UserModel;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class DataManager {
    /*public static String getToken(){
        return AndroidApplication.getSharedPreferences().getString((R.string.token_key), null);
    }*/
    private static DataManager instance = new DataManager();


    public static DataManager getInstance(){
        if (instance == null){
            instance = new DataManager();
        }
        return instance;
    }
    private static Bus mBus;
    private DataManager(){
        mBus = new Bus();
        mBus.register(this);
    }

    public static Bus getBus(){
        return mBus;
    }

    public static void getUserInfo(){
        RestService service = RestFactory.getRestService();
        Callback<UserModel> a = new Callback<UserModel>() {
            @Override
            public void success(UserModel user, Response response) {
                if (response.getStatus() == 200){
                    AndroidApplication.setUser(user);
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
                if (error.getResponse().getStatus() == 403){
                    Toast.makeText(AndroidApplication.getContext(), "Логин или пароль неверны!", Toast.LENGTH_LONG).show();
                }
                else if (error.getResponse().getStatus() == 400){
                    Toast.makeText(AndroidApplication.getContext(), "Введенные данные некорректны!", Toast.LENGTH_LONG).show();
                }
                getBus().post(new LoginFailedEvent());
            }
        };
        service.signIn("password", "mobileV1", "abc123456", login, password, callback);
    }


}
