package com.android.data;

import com.android.domain.ThreadExecutor;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version JobExecutor, v 0.1 2019-09-10 02:31 by Abraham Ginting
 */
@Singleton
public class JobExecutor implements ThreadExecutor {

    private final ThreadPoolExecutor threadPoolExecutor;

    @Inject
    public JobExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(3, Integer.MAX_VALUE, 10, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new JobThreadFactory());
    }

    @Override
    public void execute(@NonNull Runnable runnable) {
        threadPoolExecutor.execute(runnable);
    }

    private static class JobThreadFactory implements ThreadFactory {

        private int counter = 0;

        @Override
        public Thread newThread(@NonNull Runnable runnable) {
            return new Thread(runnable, "android_" + counter++);
        }
    }

}
