package ru.medyannikov.homebank.ui.main.fragments.billListFragment;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.BillFetchEvent;
import ru.medyannikov.homebank.data.managers.events.BillInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.AndroidApplication;
import ru.medyannikov.homebank.ui.adapters.BillAdapter;

/**
 * Created by vladimir on 03.05.16.
 */
public class BillListPresenterImpl implements BillListPresenter {
    private BillListView view;

    @Inject
    DataManager manager;

    public BillListPresenterImpl(){
        AndroidApplication.component().inject(this);
        manager.getBus().register(this);
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
        manager.getBus().unregister(this);
        view = null;
    }

    @Override
    public void init(BillListView view) {
        this.view = view;
    }

    @Override
    public void updateBills() {
        //view.hideLoading();
        new AsyncTask<Void, Void, List<Bill>>() {
            @Override
            protected List<Bill> doInBackground(Void... params) {
                return manager.getAllBills();
            }

            @Override
            protected void onPostExecute(List<Bill> bills) {
                if (bills.size() != 0){
                    view.updateData(bills);
                }else{
                    view.emptyData();
                }
                view.hideLoading();
            }
        }.execute();
    }

    @Override
    public void insertBill(Bill bill) {
        if (bill != null){
            manager.sendBillAsync(bill);
        }
    }

    @Override
    public void deleteBill(Bill bill, int position) {
        bill.delete();
        view.billDeleted(position, bill);
    }

    @Override
    public void deleteBill(Bill bill) {
        bill.delete();
    }

    @Subscribe
    public void onEvent(BillInsertEvent event){
        updateBills();
    }

    @Subscribe
    public void onEvent(BillFetchEvent event){
        List<Bill> list = event.getBillList();
        if (list.size() != 0){
            view.updateData(list);
        }
        else {
           view.emptyData();
        }
        view.hideLoading();
    }
}
