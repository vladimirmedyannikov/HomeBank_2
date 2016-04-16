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

import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;

/**
 * Created by Vladimir on 12.03.2016.
 */
public class AndroidApplication extends Application {

    private static SharedPreferences sharedPreferences;
    private static Context context;
    private static JobManager jobManager;

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
        configureJobManager();
    }

    public static SharedPreferences getSharedPreferences(){
        return sharedPreferences;
    }

    public void configureJobManager(){
        Configuration.Builder builder = new Configuration.Builder(this)
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .consumerKeepAlive(120);
        jobManager = new JobManager(builder.build());
    }

    public static JobManager getJobManager(){
        return jobManager;
    }




}
