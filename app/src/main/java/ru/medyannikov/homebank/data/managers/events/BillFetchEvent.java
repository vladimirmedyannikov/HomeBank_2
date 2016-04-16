package ru.medyannikov.homebank.data.managers.events;

import java.util.List;

import ru.medyannikov.homebank.data.storage.models.Bill;

/**
 * Created by Vladimir on 01.04.2016.
 */
public class BillFetchEvent {
    private List<Bill> billList;

    public BillFetchEvent(List<Bill> list){
        billList = list;
    }

    public List<Bill> getBillList() {
        return billList;
    }
}
