package ru.medyannikov.homebank.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.List;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.storage.models.UserModel;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class AndroidApplication extends Application {
    private static SharedPreferences sharedPreferences;
    private static Context context;
    private static UserModel userModel;

    public static Context getContext(){
        return context;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);

        /*SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.token_key), "OPkJmTsbzKQp/vbpIdHZBNkhmTCnW8nSUiZ4/sk/+00=");
        editor.commit();*/

        context = this;
    }

    public static SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    public static String getToken(){
        return getSharedPreferences().getString(context.getString(R.string.token_key), null);
    }

    public static UserModel getUser(){
        return userModel;
    }

    public static void setUser(UserModel user){
        if (user != null){
            List<UserModel> userDbList = new Select()
                    .from(UserModel.class)
                    .where("idUser = ?", user.getIdUser())
                    .execute();
            if (userDbList.size() > 0){
                UserModel userDb = userDbList.get(0);
                if (userDb.getStatus() != 1){
                    //TODO update user data
                    if (userDb.getDateUpdate().compareTo(user.getDateUpdate()) >= 0 ){
                        userModel = userDb;
                    }
                    else{
                        userDb.copyParam(user);
                        userDb.save();
                    }
                }
                userDb.copyParam(user);
                userDb.setEmail(user.getEmail());
                userModel = userDb;
            }
            else
            {
                user.save();
                userModel = user;
                SharedPreferences.Editor editor = getSharedPreferences().edit();
                editor.putLong(getContext().getString(R.string.user_id), getUser().getId()).apply();
            }
        }
    }


}
