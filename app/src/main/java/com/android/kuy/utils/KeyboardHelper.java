package com.android.kuy.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version KeyboardHelper, v 0.1 2019-09-10 04:10 by Abraham Ginting
 */
public class KeyboardHelper {

    private KeyboardHelper() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    private static InputMethodManager getInputMethodManager(Context context) {
        return (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
    }

    public static void show(Activity activity) {
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public static void show(View view) {
        getInputMethodManager(view.getContext())
            .showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public static void hide(Activity activity) {
        if (activity != null && hasFocus(activity)) {
            hide(activity.getWindow().getCurrentFocus());
        }
    }

    public static void hide(View view) {
        if (view != null) {
            getInputMethodManager(view.getContext())
                .hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private static boolean hasFocus(Activity activity) {
        return activity.getCurrentFocus() != null;
    }

    /**
     * setKeyboardVisibilityListener
     */
    public static void setKeyboardVisibilityListener(View rootView,
        KeyboardVisibilityListener keyboardVisibilityListener) {
        if (rootView != null) {
            final boolean[] showKeyboard = {false};

            rootView.getViewTreeObserver().addOnGlobalLayoutListener(
                () -> {
                    Rect r = new Rect();
                    rootView.getWindowVisibleDisplayFrame(r);

                    int heightDiff = rootView.getRootView().getHeight() - (r.bottom - r.top);
                    if (heightDiff > rootView.getRootView().getHeight() / 4) {
                        showKeyboard[0] = true;
                        keyboardVisibilityListener.onKeyboardShow();
                    } else if (showKeyboard[0]) {
                        showKeyboard[0] = false;
                        keyboardVisibilityListener.onKeyboardHide();
                    }
                });
        }
    }

    public interface KeyboardVisibilityListener {

        void onKeyboardShow();

        void onKeyboardHide();

    }

    public static void showKeyboardImplicit(Context context) {
        InputMethodManager imm = (InputMethodManager) context
            .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
}
