package ru.medyannikov.homebank.ui.login;

/**
 * Created by vladimir on 02.05.16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy();
    void onStart();
    void onResume();
    void onStop();
    void signIn(String login, String password);
    void signUp(String login, String password);
    void onError();
}
