package ru.medyannikov.homebank.ui.login;

import javax.inject.Singleton;

import dagger.Component;
import ru.medyannikov.homebank.di.component.AndroidApplicationComponent;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 02.05.16.
 */
@LoginActivityScope
@Singleton
@Component(
        modules = {
                LoginActivityModule.class, AndroidApplicationModule.class
        }
)
public interface LoginActivityComponent {
    void inject(LoginActivity activity);
    void inject(LoginView view);
    void inject(LoginPresenter presenter);
    LoginPresenter presenter();
}
