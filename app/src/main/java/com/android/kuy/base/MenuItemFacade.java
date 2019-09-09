package com.android.kuy.base;

import androidx.annotation.DrawableRes;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version MenuItemFacade, v 0.1 2019-09-10 04:00 by Abraham Ginting
 */
public interface MenuItemFacade {

    /**
     * set toolbar Menu LeftButton
     */
    void setMenuLeftButton(String leftButtonText);

    /**
     * set toolbar Menu LeftButton enabled
     */
    void setMenuLeftButtonEnabled(boolean isEnabled);

    /**
     * set toolbar Menu RightButton
     */
    void setMenuRightButton(String rightButtonText);

    /**
     * set toolbar Menu RightButton enabled
     */
    void setMenuRightButtonEnabled(boolean isEnabled);

    /**
     * set toolbar Menu RightButton with Progress Bar enabled
     */
    void setMenuRightProgressBar(boolean isProgress);

    /**
     * set toolbar Menu LeftButton Drawable
     */
    void setMenuLeftButton(@DrawableRes int icon);

}
