package com.android.kuy.base;

import android.view.View;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version SingleClickListener, v 0.1 2019-09-10 01:46 by Abraham Ginting
 */
public abstract class SingleClickListener implements View.OnClickListener {

    MultipleClickHandler multipleClickHandler;

    public SingleClickListener(MultipleClickHandler multipleClickHandler) {
        this.multipleClickHandler = multipleClickHandler;
    }

    @Override
    public void onClick(View v) {
        if (multipleClickHandler != null && multipleClickHandler.isClickable()) {
            multipleClickHandler.disableClick();
            singleClick(v);
        } else if (multipleClickHandler == null) {
            singleClick(v);
        }
    }

    public abstract void singleClick(View view);
}
