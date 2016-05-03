package ru.medyannikov.homebank.ui.main.fragments;

/**
 * Created by vladimir on 03.05.16.
 */
public interface BasePresenterFragment<T> {
    void onStart();
    void onStop();
    void onResume();
    void onDestroy();
    void init(T view);
}
