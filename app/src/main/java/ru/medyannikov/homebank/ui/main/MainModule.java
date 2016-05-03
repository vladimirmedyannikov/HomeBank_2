package ru.medyannikov.homebank.ui.main;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;
import ru.medyannikov.homebank.ui.AndroidApplication;
import ru.medyannikov.homebank.ui.login.LoginActivityScope;
import ru.medyannikov.homebank.ui.main.fragments.billListFragment.BillListPresenter;
import ru.medyannikov.homebank.ui.main.fragments.billListFragment.BillListPresenterImpl;
import ru.medyannikov.homebank.ui.main.fragments.profileFragment.ProfilePresenter;
import ru.medyannikov.homebank.ui.main.fragments.profileFragment.ProfilePresenterImpl;

/**
 * Created by vladimir on 03.05.16.
 */
@Module
public class MainModule {
    protected MainActivityView activity;

    public MainModule(MainActivityView activity){
        this.activity = activity;
    }

    @Provides
    @MainActivityScope
    MainActivityView provideMainActivityView(){
        return activity;
    }

    @Provides
    @Singleton
    MainActivityPresenter provideMainActivityPresenter(MainActivityView view){
        return new MainActivityPresenterImpl(view);
    }

    @Provides
    @Singleton
    ProfilePresenter provideProfilePresenter(){
        return new ProfilePresenterImpl();
    }

    @Provides
    @Singleton
    BillListPresenter provideBillListPresenter(){
        return new BillListPresenterImpl();
    }
}
