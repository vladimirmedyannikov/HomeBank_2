package ru.medyannikov.homebank.ui.main.fragments.billListFragment;

import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.main.fragments.BasePresenterFragment;

/**
 * Created by vladimir on 03.05.16.
 */
public interface BillListPresenter extends BasePresenterFragment<BillListView> {
    void updateBills();
    void insertBill(Bill bill);
    void deleteBill(Bill bill, int position);
    void deleteBill(Bill bill);
}
