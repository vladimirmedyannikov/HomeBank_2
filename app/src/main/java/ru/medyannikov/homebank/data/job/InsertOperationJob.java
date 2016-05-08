package ru.medyannikov.homebank.data.job;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.OperationInsertEvent;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.data.storage.models.Operation;
import ru.medyannikov.homebank.ui.AndroidApplication;

/**
 * Created by vladimir on 18.04.16.
 */
public class InsertOperationJob extends BaseJob {
    private Operation operation;
    private Account account;
    @Inject
    DataManager manager;
    @Inject
    Bus bus;

    public InsertOperationJob(Operation operation, Account currentAccount) {
        super(new Params(BACKGROUND).requireNetwork().persist());
        this.operation = operation;
        this.account = currentAccount;
        AndroidApplication.component().inject(this);
    }

    @Override
    public void onAdded() {
        operation.save();
        operation.getBill().calcucateSumm();
        bus.post(new OperationInsertEvent(operation));
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
