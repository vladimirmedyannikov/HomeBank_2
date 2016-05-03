package ru.medyannikov.homebank.ui.main.fragments.profileFragment;

import ru.medyannikov.homebank.ui.main.fragments.BasePresenterFragment;

/**
 * Created by vladimir on 03.05.16.
 */
public interface ProfilePresenter extends BasePresenterFragment<ProfileView> {
    void onStart();
    void onStop();
    void onResume();
    void onDestroy();
    void showProfile();
}
