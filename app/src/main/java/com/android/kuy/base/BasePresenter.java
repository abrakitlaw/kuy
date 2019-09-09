package com.android.kuy.base;

import dagger.internal.Preconditions;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BasePresenter, v 0.1 2019-09-10 02:00 by Abraham Ginting
 */
public abstract class BasePresenter implements DisposableHandler {

    private CompositeDisposable disposables;

    @Override
    public void addDisposable(Disposable disposable) {
        if (disposables == null) {
            disposables = new CompositeDisposable();
        }

        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }

    @Override
    public void dispose() {
        if (disposables != null && !disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
