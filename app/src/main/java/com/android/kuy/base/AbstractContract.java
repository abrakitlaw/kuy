package com.android.kuy.base;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version AbstractContract, v 0.1 2019-09-07 02:05 by Abraham Ginting
 */
public interface AbstractContract {

    interface AbstractView {

        void showProgress();

        void dismissProgress();

        void onError(String errorMessage);

    }

    interface AuthenticatedAbstractView extends AbstractView {

        void onSessionExpired();

    }

    interface AbstractPresenter {

        void onDestroy();

    }
}
