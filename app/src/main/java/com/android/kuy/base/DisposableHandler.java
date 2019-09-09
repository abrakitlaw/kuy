package com.android.kuy.base;

import io.reactivex.disposables.Disposable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version DisposableHandler, v 0.1 2019-09-10 02:00 by Abraham Ginting
 */
public interface DisposableHandler {

    void addDisposable(Disposable disposable);

    void dispose();

}
