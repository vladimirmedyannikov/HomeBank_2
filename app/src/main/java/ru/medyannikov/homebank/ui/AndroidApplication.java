package ru.medyannikov.homebank.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.query.Select;
import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;

import java.util.List;

import javax.inject.Inject;

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.DataModule;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.di.component.AndroidApplicationComponent;

import ru.medyannikov.homebank.di.component.DaggerAndroidApplicationComponent;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class AndroidApplication extends Application {
    private static AndroidApplicationComponent appComponent;

    @Inject SharedPreferences sharedPreferences;
    @Inject DataManager manager;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponentAndInject();
        ActiveAndroid.initialize(this);
    }

    private void buildComponentAndInject() {
        appComponent = DaggerAndroidApplicationComponent.builder()
                .androidApplicationModule(new AndroidApplicationModule(this))
                .dataModule(new DataModule())
                .build();
        appComponent.inject(this);
    }


    public static AndroidApplicationComponent component() {return appComponent;}

    public static AndroidApplication get(Context context){
        return (AndroidApplication) context.getApplicationContext();
    }

    /*public Context getContext(){
        return getApplicationContext();
    }*/


}
