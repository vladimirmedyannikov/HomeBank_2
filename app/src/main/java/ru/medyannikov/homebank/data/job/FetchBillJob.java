package ru.medyannikov.homebank.data.job;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import java.util.List;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.BillFetchEvent;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.data.storage.models.Bill;

/**
 * Created by Vladimir on 28.03.2016.
 */
public class FetchBillJob extends BaseJob {
    private Account account;
    private List<Bill> billList;
    public FetchBillJob(Account currentAccount) {
        super(new Params(BACKGROUND).requireNetwork().persist());
        this.account = currentAccount;
    }

    @Override
    public void onAdded() {
        billList = DataManager.getInstance().getAllBills();
        DataManager.getBus().post(new BillFetchEvent(billList));
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
