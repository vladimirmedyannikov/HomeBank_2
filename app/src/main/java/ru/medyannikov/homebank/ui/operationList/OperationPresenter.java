package ru.medyannikov.homebank.ui.operationList;

/**
 * Created by vladimir on 04.05.16.
 */
public interface OperationPresenter {
    void onCreate();
    void onPause();
    void onStop();
    void onDestroy();
    void onResume();
}
