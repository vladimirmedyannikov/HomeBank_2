package ru.medyannikov.homebank.ui.operationList.fragments;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.OperationFetchEvent;
import ru.medyannikov.homebank.data.managers.events.OperationInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.AndroidApplication;
import ru.medyannikov.homebank.ui.operationList.OperationPresenterImpl;

/**
 * Created by vladimir on 04.05.16.
 */
public class OperationListFragmentPresenterImpl implements OperationListFragmentPresenter {
    private OperationListFragmentView view;

    @Inject
    DataManager manager;

    @Inject
    Bus bus;

    public OperationListFragmentPresenterImpl(){
        AndroidApplication.component().inject(this);
        bus.register(this);
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
        bus.unregister(this);
    }

    @Override
    public void attachView(OperationListFragmentView view) {
        this.view = view;
    }

    @Override
    public void deleteOperation(Operation operation, int position) {
        operation.deleteAndCalc();
        view.operationDeleted(position);
    }

    @Override
    public void updateData(Bill bill) {
        manager.fetchOperationAsync(bill);
    }

    @Override
    public void insertOperation(Operation operation) {
        manager.sendOperationAsync(operation);
    }

    @Subscribe
    public void onEvent(OperationInsertEvent event){
        view.operationInserted(event.getItem());
    }

    @Subscribe
    public void onEvent(OperationFetchEvent event){
        view.setData(event.getItems());
    }
}
