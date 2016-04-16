package ru.medyannikov.homebank.data.job;


import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;

/**
 * Created by Vladimir on 28.03.2016.
 */
abstract public class BaseJob extends Job {
    public static final int UI_HIGH = 10;
    public static final int BACKGROUND = 10;

    protected BaseJob(Params params) {
        super(params);
    }
}
