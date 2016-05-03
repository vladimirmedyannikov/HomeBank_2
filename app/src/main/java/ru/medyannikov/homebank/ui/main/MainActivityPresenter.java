package ru.medyannikov.homebank.ui.main;

/**
 * Created by vladimir on 02.05.16.
 */
public interface MainActivityPresenter {
    void onCreate();
    void onPause();
    void onStop();
    void onDestroy();
    void onResume();
}
