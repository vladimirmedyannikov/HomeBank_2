package ru.medyannikov.homebank.data.job;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.OperationFetchEvent;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 18.04.16.
 */
public class FetchOperationJob extends BaseJob {
    private Bill bill;
    private Account account;

    @Inject
    DataManager manager;
    @Inject
    Bus bus;

    public FetchOperationJob(Bill bill, Account currentAccount) {
        super(new Params(BACKGROUND).requireNetwork().persist());
        this.account = currentAccount;
        this.bill = bill;
        AndroidApplication.component().inject(this);
    }

    @Override
    public void onAdded() {
        List<Operation> operationList = manager.getOperations(bill);
        bus.post(new OperationFetchEvent(operationList));
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
