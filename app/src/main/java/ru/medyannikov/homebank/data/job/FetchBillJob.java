package ru.medyannikov.homebank.data.job;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.BillFetchEvent;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by Vladimir on 28.03.2016.
 */
public class FetchBillJob extends BaseJob {
    private Account account;
    private List<Bill> billList;
    @Inject
    DataManager manager;
    @Inject
    Bus bus;

    public FetchBillJob(Account currentAccount) {
        super(new Params(BACKGROUND).requireNetwork().persist());
        this.account = currentAccount;
        AndroidApplication.component().inject(this);
    }

    @Override
    public void onAdded() {
        billList = manager.getAllBills();
        bus.post(new BillFetchEvent(billList));
    }

    @Override
    public void onRun() throws Throwable {

    }

    @Override
    protected void onCancel(int cancelReason) {

    }

    @Override
    protected RetryConstraint shouldReRunOnThrowable(Throwable throwable, int runCount, int maxRunCount) {
        return RetryConstraint.createExponentialBackoff(runCount, 1500);
    }
}
