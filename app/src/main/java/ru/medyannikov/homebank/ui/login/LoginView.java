package ru.medyannikov.homebank.ui.login;

/**
 * Created by vladimir on 02.05.16.
 */
public interface LoginView {
    void showError(String msg);
    void auth();
    void showProgress();
    void hideProgress();
}
