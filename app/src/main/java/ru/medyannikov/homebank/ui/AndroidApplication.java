package ru.medyannikov.homebank.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;

import java.util.List;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class AndroidApplication extends Application {

    private static SharedPreferences sharedPreferences;
    private static Context context;

    public static Context getContext(){
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
        sharedPreferences = getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        context = this;
        DataManager.initializeUser();
    }

    public static SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }




}
