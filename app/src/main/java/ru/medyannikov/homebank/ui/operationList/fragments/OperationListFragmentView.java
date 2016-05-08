package ru.medyannikov.homebank.ui.operationList.fragments;

import java.util.List;

import ru.medyannikov.homebank.data.storage.models.Operation;

/**
 * Created by vladimir on 04.05.16.
 */
public interface OperationListFragmentView {
    void setData(List<Operation> listOperation);
    void operationDeleted(int position);
    void operationInserted(Operation operation);
}
