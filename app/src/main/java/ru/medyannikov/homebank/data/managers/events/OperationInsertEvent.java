package ru.medyannikov.homebank.data.managers.events;

import ru.medyannikov.homebank.data.storage.models.Operation;

/**
 * Created by vladimir on 18.04.16.
 */
public class OperationInsertEvent {
    private Operation operation;
    public OperationInsertEvent()
    {
        //this.operation = operation;
    }

    public Operation getItem(){
        return operation;
    }
}
