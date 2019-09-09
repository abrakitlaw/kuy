package com.android.kuy.di.module;

import com.android.kuy.base.BaseActivity;
import com.android.kuy.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version ActivityModule, v 0.1 2019-09-10 02:15 by Abraham Ginting
 */
@Module
public class ActivityModule {

    private final BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides
    @PerActivity
    BaseActivity baseActivity() {
        return baseActivity;
    }
}
