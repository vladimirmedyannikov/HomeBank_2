package ru.medyannikov.homebank.ui.login;

import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.LoginFailedEvent;
import ru.medyannikov.homebank.data.managers.events.LoginSuccessEvent;
import ru.medyannikov.homebank.data.managers.events.NetworkStatusError;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 02.05.16.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private final LoginView view;

    @Inject
    DataManager manager;

    public LoginPresenterImpl(LoginView view){
        this.view = view;
        AndroidApplication.component().inject(this);
        manager.getBus().register(this);
    }

    @Override
    public void onCreate() {
        if (manager!= null && manager.isLogged()){
            view.auth();
        }
    }

    @Override
    public void onDestroy() {
        manager.getBus().unregister(this);
    }

    @Override
    public void onStart() {
        String token = manager.getToken();
        view.auth();
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void signIn(String login, String password) {
        view.showProgress();
        if (manager != null){
            manager.signIn(login, password);
        }
    }

    @Override
    public void signUp(String login, String password) {

    }

    @Override
    public void onError() {

    }

    @Subscribe
    public void onEvent(LoginSuccessEvent event){
        manager.setToken(event.getTokenModel().getAccessToken());
        //DataManager.getInstance().auth();
        view.hideProgress();
        view.auth();
    }

    @Subscribe
    public void onEvent(LoginFailedEvent event){
        view.hideProgress();
        view.showError(event.getError());
    }

    @Subscribe
    public void onEvent(NetworkStatusError event){
        view.showError("Отсутствует интернет соединение");
    }
}
