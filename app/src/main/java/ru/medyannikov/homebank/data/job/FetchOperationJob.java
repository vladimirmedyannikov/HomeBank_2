package ru.medyannikov.homebank.data.job;

import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import java.util.List;

import ru.medyannikov.homebank.data.managers.DataManager;
import ru.medyannikov.homebank.data.managers.events.OperationFetchEvent;
import ru.medyannikov.homebank.data.storage.models.Account;
import ru.medyannikov.homebank.data.storage.models.Bill;
import ru.medyannikov.homebank.data.storage.models.Operation;

/**
 * Created by vladimir on 18.04.16.
 */
public class FetchOperationJob extends BaseJob {
    private Bill bill;
    private Account account;

    public FetchOperationJob(Bill bill, Account currentAccount) {
        super(new Params(BACKGROUND).requireNetwork().persist());
        this.account = currentAccount;
        this.bill = bill;
    }

    @Override
    public void onAdded() {
        List<Operation> operationList = DataManager.getOperations(bill);
        DataManager.getBus().post(new OperationFetchEvent(operationList));
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
