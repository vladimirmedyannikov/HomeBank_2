package ru.medyannikov.homebank.data.job;



import android.util.Log;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import java.util.Date;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.BillInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.data.storage.models.Bill;

/**
 * Created by Vladimir on 28.03.2016.
 */
public class InsertBillJob extends BaseJob {
    private Bill newBill;
    private Account account;

    public InsertBillJob(Bill insertBill, Account currentAccount) {
        super(new Params(BACKGROUND).requireNetwork().persist());
        newBill = insertBill;
        account = currentAccount;
    }

    /**
     * Save bill on DB
     */
    @Override
    public void onAdded() {
        newBill.setDate(new Date(System.currentTimeMillis() + 1));
        newBill.save();
        DataManager.getBus().post(new BillInsertEvent());
    }

    /**
     * Save bill in the remote server
     * @throws Throwable
     */
    @Override
    public void onRun() throws Throwable {

    }

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return null;
    }

}
