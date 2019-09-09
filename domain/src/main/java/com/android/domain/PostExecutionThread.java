package com.android.domain;

import io.reactivex.Scheduler;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version PostExecutionThread, v 0.1 2019-09-10 02:28 by Abraham Ginting
 */
public interface PostExecutionThread {

    Scheduler getScheduler();

}
