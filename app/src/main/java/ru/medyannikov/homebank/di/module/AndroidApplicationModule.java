package ru.medyannikov.homebank.di.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.medyannikov.homebank.R;
import ru.medyannikov.homebank.data.DataModule;
import ru.medyannikov.homebank.di.scope.AppScope;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 30.04.16.
 */
@Module(
        includes = DataModule.class
)
public class AndroidApplicationModule {
    protected AndroidApplication application;

    public AndroidApplicationModule(AndroidApplication application){
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideAndroidApplication(){
        return application.getApplicationContext();
    }

    @Provides
    Application provideApplication(){
        return application;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application app){
        return app.getSharedPreferences(app.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    }
}
