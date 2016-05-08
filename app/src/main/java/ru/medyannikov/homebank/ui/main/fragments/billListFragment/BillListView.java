package ru.medyannikov.homebank.ui.main.fragments.billListFragment;

import java.util.List;

import ru.medyannikov.homebank.data.storage.models.Bill;

/**
 * Created by vladimir on 03.05.16.
 */
public interface BillListView {
    void updateData(List<Bill> bills);
    void showInsertDialog();
    void showLoading();
    void hideLoading();
    void billDeleted(int positionAdapter, Bill bill);
    void billDeleted();
    void emptyData();
    void billInserted(Bill bill);
}
