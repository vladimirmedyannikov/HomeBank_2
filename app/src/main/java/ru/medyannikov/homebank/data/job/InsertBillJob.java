package ru.medyannikov.homebank.data.job;



import android.util.Log;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.squareup.otto.Bus;

import java.util.Date;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.BillInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by Vladimir on 28.03.2016.
 */
public class InsertBillJob extends BaseJob {
    private Bill newBill;
    private Account account;
    @Inject
    Bus bus;
    @Inject
    DataManager manager;

    public InsertBillJob(Bill insertBill, Account currentAccount) {
        super(new Params(BACKGROUND).requireNetwork());
        newBill = insertBill;
        account = currentAccount;
        AndroidApplication.component().inject(this);
    }

    /**
     * Save bill on DB
     */
    @Override
    public void onAdded() {
        newBill.setDate(new Date(System.currentTimeMillis() + 1));
        newBill.setAccount(account);
        newBill.save();
        try {
            bus.post(new BillInsertEvent(newBill));
        }
        catch (Exception e){

        }
    }

    /**
     * Save bill in the remote server
     * @throws Throwable
     */
    @Override
    public void onRun() throws Throwable {
        manager.insertBill(newBill);
    }

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }

}
