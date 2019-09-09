package com.android.kuy.base;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version PresenterHandler, v 0.1 2019-09-10 02:04 by Abraham Ginting
 */
public interface PresenterHandler {

    void registerPresenter(AbstractContract.AbstractPresenter... presenters);

    void disposePresenter();

}
