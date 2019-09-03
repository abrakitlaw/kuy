package com.android.kuy.di.component;

import com.android.kuy.KuyApplication;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version ApplicationComponent, v 0.1 2019-09-04 04:13 by Abraham Ginting
 */
@Singleton
@Component()
public interface ApplicationComponent {

    void inject(KuyApplication kuyApplication);
}
