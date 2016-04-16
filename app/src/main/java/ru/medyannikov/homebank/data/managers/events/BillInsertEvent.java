package ru.medyannikov.homebank.data.managers.events;

import ru.medyannikov.homebank.data.storage.models.Bill;

/**
 * Created by Vladimir on 28.03.2016.
 */
public class BillInsertEvent {
    private Bill bill;

    public BillInsertEvent(Bill insertedBill){
        this.bill = insertedBill;
    }

    public BillInsertEvent(){
    }

    public Bill getBill() {
        return bill;
    }


}
