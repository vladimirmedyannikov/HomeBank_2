package ru.medyannikov.homebank.ui.login;

import dagger.Module;
import dagger.Provides;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;

/**
 * Created by vladimir on 02.05.16.
 */

@Module
public class LoginActivityModule {
    protected LoginView view;

    public LoginActivityModule(LoginView view){
        this.view = view;
    }

    @Provides
    @LoginActivityScope
    LoginView provideLoginView(){
        return view;
    }

    @Provides
    @LoginActivityScope
    LoginPresenter provideLoginPresenter(LoginView view){
        return new LoginPresenterImpl(view);
    }
}
