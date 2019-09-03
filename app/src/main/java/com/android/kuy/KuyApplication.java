package com.android.kuy;

import com.android.kuy.di.component.ApplicationComponent;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version KuyApplication, v 0.1 2019-09-04 03:42 by Abraham Ginting
 */
public class KuyApplication extends MultiDexApplication {

    private ApplicationComponent applicationComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new Timber.Tree() {
                @Override
                protected void log(int priority, @Nullable String tag, @NotNull String message,
                    @Nullable Throwable t) {
                    //do nothing
                }
            });
        }

        setGlobalRxExceptionHandler();
    }

    private void setGlobalRxExceptionHandler() {
        if (RxJavaPlugins.getErrorHandler() != null) {
            return;
        }

        RxJavaPlugins.setErrorHandler(e -> {
            if (e instanceof UndeliverableException) {
                e = e.getCause();
            }

            //blocking code disposed
            if (e instanceof InterruptedException) {
                Timber.e(e);
                return;
            }
            //unexpected bug in the stream
            if ((e instanceof NullPointerException) || (e instanceof IllegalArgumentException)) {
                Timber.e(e);
                Thread.currentThread().getUncaughtExceptionHandler()
                    .uncaughtException(Thread.currentThread(), e);
                return;
            }
            //bug in RxJava or custom operator
            if (e instanceof IllegalStateException) {
                Timber.e(e);
                Thread.currentThread().getUncaughtExceptionHandler()
                    .uncaughtException(Thread.currentThread(), e);
                return;
            }

            //other unexpected undeliverable exception
            Timber.e(e);
        });
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public int getNetworkType() {
        try {
            ConnectivityManager cm = (ConnectivityManager) getSystemService(
                Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork.getType();
        } catch (Exception e) {
            return 0;
        }
    }
}
