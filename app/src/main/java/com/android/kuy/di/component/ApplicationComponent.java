package com.android.kuy.di.component;

import com.android.domain.PostExecutionThread;
import com.android.domain.ThreadExecutor;
import com.android.kuy.KuyApplication;
import com.android.kuy.base.BaseActivity;
import com.android.kuy.di.module.ApplicationModule;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version ApplicationComponent, v 0.1 2019-09-04 04:13 by Abraham Ginting
 */
@Singleton
@Component(modules = {
    ApplicationModule.class,

})
public interface ApplicationComponent {

    void inject(KuyApplication kuyApplication);

    void inject(BaseActivity baseActivity);

    Context context();

    Application application();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}
