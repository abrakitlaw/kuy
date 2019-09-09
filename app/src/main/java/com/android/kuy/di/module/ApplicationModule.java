package com.android.kuy.di.module;

import com.android.data.JobExecutor;
import com.android.domain.PostExecutionThread;
import com.android.domain.ThreadExecutor;
import com.android.kuy.UIThread;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version ApplicationModule, v 0.1 2019-09-04 04:14 by Abraham Ginting
 */
@Module
public class ApplicationModule {

    private final Application application;

    public ApplicationModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }
}
