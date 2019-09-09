package com.android.data;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import androidx.annotation.StringDef;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version Source, v 0.1 2019-09-10 02:49 by Abraham Ginting
 *
 * Choices of data source
 * LOCAL = persistence data (example : shared preference, local db, etc)
 * NETWORK = from BE
 * MOCK = mock data in app
 */
@StringDef({
    Source.LOCAL,
    Source.NETWORK,
    Source.MOCK
})
@Retention(RetentionPolicy.SOURCE)
public @interface Source {

    String LOCAL = "local";

    String NETWORK = "network";

    String MOCK = "mock";

}
