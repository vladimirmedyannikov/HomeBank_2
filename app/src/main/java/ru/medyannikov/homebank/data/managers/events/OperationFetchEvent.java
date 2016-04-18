package ru.medyannikov.homebank.data.managers.events;

import java.util.List;

import ru.medyannikov.homebank.data.storage.models.Operation;

/**
 * Created by vladimir on 18.04.16.
 */
public class OperationFetchEvent {
    private List<Operation> operationList;

    public OperationFetchEvent(List<Operation> list){
        this.operationList = list;
    }

    public List<Operation> getItems(){
        return operationList;
    }
}
