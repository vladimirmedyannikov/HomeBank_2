package ru.medyannikov.homebank.ui.main;

import android.util.Log;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 02.05.16.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {
    private MainActivityView view;

    @Inject
    DataManager manager;

    public MainActivityPresenterImpl(MainActivityView view){
        this.view = view;
        AndroidApplication.component().inject(this);
        if (manager != null) Log.e("TEST","Create mainActivityImpl "+this.toString());
    }
    @Override
    public void onCreate() {
        if (manager != null){
            manager.auth();
        }
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onResume() {
        Log.e("TEST","Resume mainActivityImpl "+this.toString());
    }
}
