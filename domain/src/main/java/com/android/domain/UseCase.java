package com.android.domain;

import dagger.internal.Preconditions;
import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version UseCase, v 0.1 2019-09-10 02:46 by Abraham Ginting
 *
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 *
 * By convention each UseCase implementation will return the result using a
 * {@link DisposableObserver}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase<T, Params> {

    /** threadExecutor */
    private final ThreadExecutor threadExecutor;

    /** postExecutionThread */
    private final PostExecutionThread postExecutionThread;

    /** disposables */
    private final CompositeDisposable disposables;

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
        disposables = new CompositeDisposable();
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}
     *
     * @param params Parameters (Optional) used to build/execute this use case.
     * @return Observable
     */
    protected abstract Observable<T> buildUseCaseObservable(Params params);

    /**
     * Execute the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(Params)} ()} method.
     */
    public void execute(DisposableObserver<T> observer) {
        execute(observer, null);
    }

    /**
     * Execute the current use case.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void execute(DisposableObserver<T> observer, Params params) {
        Preconditions.checkNotNull(observer);

        final Observable<T> observable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor))
            .observeOn(postExecutionThread.getScheduler());

        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Execute the current use case in background thread.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(Params)} ()} method.
     */
    public void executeInBackground(DisposableObserver<T> observer) {
        executeInBackground(observer, null);
    }

    /**
     * Execute the current use case in background thread.
     *
     * @param observer {@link DisposableObserver} which will be listening to the observable build
     *                 by {@link #buildUseCaseObservable(Params)} ()} method.
     * @param params   Parameters (Optional) used to build/execute this use case.
     */
    public void executeInBackground(DisposableObserver<T> observer, Params params) {
        Preconditions.checkNotNull(observer);

        final Observable<T> observable = buildUseCaseObservable(params)
            .subscribeOn(Schedulers.from(threadExecutor));

        addDisposable(observable.subscribeWith(observer));
    }

    /**
     * Dispose from current {@link CompositeDisposable}
     */
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }

    /**
     * Dispose from current {@link CompositeDisposable}
     *
     * @param disposable {@link Disposable}
     */
    private void addDisposable(Disposable disposable) {
        Preconditions.checkNotNull(disposable);
        Preconditions.checkNotNull(disposables);
        disposables.add(disposable);
    }

}
