package com.android.domain;

import io.reactivex.observers.DisposableObserver;
import timber.log.Timber;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version DefaultObserver, v 0.1 2019-09-10 02:44 by Abraham Ginting
 */
public class DefaultObserver<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        Timber.e(e);
    }

    @Override
    public void onComplete() {

    }
}
