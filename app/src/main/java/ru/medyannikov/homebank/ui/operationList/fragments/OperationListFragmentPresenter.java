package ru.medyannikov.homebank.ui.operationList.fragments;

import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.main.fragments.BasePresenterFragment;

/**
 * Created by vladimir on 04.05.16.
 */
public interface OperationListFragmentPresenter extends BasePresenterFragment<OperationListFragmentView> {
    void deleteOperation(Operation operation, int position);
    void updateData(Bill bill);
    void insertOperation(Operation operation);
}
