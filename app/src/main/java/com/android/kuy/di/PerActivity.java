package com.android.kuy.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version PerActivity, v 0.1 2019-09-04 04:11 by Abraham Ginting
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
