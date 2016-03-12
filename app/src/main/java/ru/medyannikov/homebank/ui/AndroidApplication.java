package ru.medyannikov.homebank.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import ru.medyannikov.homebank.R;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class AndroidApplication extends Application {
    private static SharedPreferences sharedPreferences;
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.token_key), "OPkJmTsbzKQp/vbpIdHZBNkhmTCnW8nSUiZ4/sk/+00=");
        editor.commit();
        context = this;
    }

    public static SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    public static String getToken(){
        return getSharedPreferences().getString(context.getString(R.string.token_key), null);
    }


}
