package ru.medyannikov.homebank.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.birbit.android.jobqueue.JobManager;
import com.birbit.android.jobqueue.config.Configuration;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.di.scope.AppScope;

/**
 * Created by vladimir on 01.05.16.
 */
@Module
public class DataModule {

    @Provides
    @Singleton
    DataManager provideDataManager(){
        return DataManager.getInstance();
    }

    @Provides
    @Singleton
    JobManager provideJobManager(Application context){
        Configuration.Builder builder = new Configuration.Builder(context)
                .minConsumerCount(1)
                .maxConsumerCount(3)
                .loadFactor(3)
                .consumerKeepAlive(120);
        return new JobManager(builder.build());
    }
}
