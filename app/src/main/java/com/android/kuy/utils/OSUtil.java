package com.android.kuy.utils;

import android.os.Build;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version OSUtil, v 0.1 2019-09-10 04:09 by Abraham Ginting
 */
public class OSUtil {

    public static boolean isJellyBeanMR1AndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1;
    }

    public static boolean isJellyBeanMR2AndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isJellyBeanMR2AndBelow() {
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2;
    }

    public static boolean isKitkatAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }

    public static boolean isLollipopAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }

    public static boolean isMarshmallowAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    public static boolean isMarshmallowAbove() {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.M;
    }

    public static boolean isNougatAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N;
    }

    public static boolean isOreoBelow() {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.O;
    }

    public static boolean isOreoAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }

    public static boolean isNougat() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.N ||
            Build.VERSION.SDK_INT == Build.VERSION_CODES.N_MR1;
    }

    public static boolean isOreo() {
        return Build.VERSION.SDK_INT == Build.VERSION_CODES.O ||
            Build.VERSION.SDK_INT == Build.VERSION_CODES.O_MR1;
    }

    public static boolean isLolipopBelow() {
        return Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1;
    }

    public static boolean isPieAndAbove() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.P;
    }
}
