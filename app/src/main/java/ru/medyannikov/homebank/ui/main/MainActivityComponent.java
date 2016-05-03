package ru.medyannikov.homebank.ui.main;

import javax.inject.Singleton;

import dagger.Component;
import ru.medyannikov.homebank.di.component.AndroidApplicationComponent;
import ru.medyannikov.homebank.di.module.AndroidApplicationModule;
import ru.medyannikov.homebank.ui.main.fragments.billListFragment.BillsListFragment;
import ru.medyannikov.homebank.ui.main.fragments.profileFragment.ProfileFragment;
import ru.medyannikov.homebank.ui.main.fragments.profileFragment.ProfilePresenter;

/**
 * Created by vladimir on 02.05.16.
 */
@MainActivityScope
@Singleton
@Component(
        modules = {
                MainModule.class, AndroidApplicationModule.class
        }
)
public interface MainActivityComponent {
    void inject(MainActivity activity);
    void inject(MainActivityPresenter presenter);
    void inject(MainActivityView view);
    void inject(ProfilePresenter presenter);
    void inject(ProfileFragment fragment);
    void inject(BillsListFragment fragment);
    ProfilePresenter profilePresenter();
    MainActivityPresenter mainPresenter();
}
