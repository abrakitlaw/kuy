package com.android.kuy;

import com.android.domain.PostExecutionThread;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version UIThread, v 0.1 2019-09-10 02:37 by Abraham Ginting
 */
@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
