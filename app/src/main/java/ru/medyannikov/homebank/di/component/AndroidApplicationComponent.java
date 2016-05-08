package ru.medyannikov.homebank.di.component;

import android.app.IntentService;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Component;
import ru.medyannikov.homebank.data.DataModule;
import ru.medyannikov.homebank.data.gcm.RegistrationIntentService;
import ru.medyannikov.homebank.data.job.FetchBillJob;
import ru.medyannikov.homebank.data.job.FetchOperationJob;
import ru.medyannikov.homebank.data.job.InsertBillJob;
import ru.medyannikov.homebank.data.job.InsertOperationJob;
import ru.medyannikov.homebank.ui.main.MainActivity;
import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;
import ru.medyannikov.homebank.ui.AndroidApplication;
import ru.medyannikov.homebank.ui.login.LoginPresenterImpl;
import ru.medyannikov.homebank.ui.main.MainActivityPresenterImpl;
import ru.medyannikov.homebank.ui.main.fragments.billListFragment.BillListPresenterImpl;
import ru.medyannikov.homebank.ui.main.fragments.profileFragment.ProfilePresenter;
import ru.medyannikov.homebank.ui.main.fragments.profileFragment.ProfilePresenterImpl;
import ru.medyannikov.homebank.ui.operationList.fragments.OperationListFragmentPresenterImpl;

/**
 * Created by vladimir on 30.04.16.
 */
@Singleton
@Component(
        modules = {
                AndroidApplicationModule.class,
                DataModule.class
        }
)
public interface AndroidApplicationComponent {
    void inject(AndroidApplication app);
    //void inject(MainActivity mainActivity);
    //void inject(LoginActivity activity);
    void inject(RegistrationIntentService service);
    void inject(DataManager manager);
    void inject(LoginPresenterImpl presenterImp);
    void inject(MainActivityPresenterImpl presenterImpl);
    void inject(ProfilePresenterImpl presenterImpl);
    void inject(BillListPresenterImpl presenterImpl);
    void inject(OperationListFragmentPresenterImpl presenterImpl);
    void inject(FetchBillJob job);
    void inject(FetchOperationJob job);
    void inject(InsertOperationJob job);
    void inject(InsertBillJob job);
    Context context();
    SharedPreferences sharedPreferences();
}
