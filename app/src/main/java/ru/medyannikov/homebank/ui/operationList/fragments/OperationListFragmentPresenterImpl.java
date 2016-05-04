package ru.medyannikov.homebank.ui.operationList.fragments;

import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.operationList.OperationPresenterImpl;

/**
 * Created by vladimir on 04.05.16.
 */
public class OperationListFragmentPresenterImpl implements OperationListFragmentPresenter {
    private OperationListFragmentView view;

    public OperationListFragmentPresenterImpl(){

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void attachView(OperationListFragmentView view) {
        this.view = view;
    }

    @Override
    public void deleteOperation(Operation operation) {

    }

    @Override
    public void updateData() {

    }
}
