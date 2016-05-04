package ru.medyannikov.homebank.ui.main.fragments.profileFragment;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 03.05.16.
 */
public class ProfilePresenterImpl implements ProfilePresenter {
    private ProfileView view;

    @Inject
    DataManager manager;

    public ProfilePresenterImpl(){
        AndroidApplication.component().inject(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void showProfile() {
        if (manager != null){
            manager.initializeUser();
            Account account = manager.getAccount();
            view.onUpdateData(account);
        }
    }

    @Override
    public void attachView(ProfileView view) {
        this.view = view;
    }
}
