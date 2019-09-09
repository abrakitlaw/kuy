package com.android.kuy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version SizeUtil, v 0.1 2019-09-10 02:21 by Abraham Ginting
 */
public class SizeUtil {

    private static final int INDEX_Y_POSISITON = 1;

    private static final String STATUS_BAR_IDENTIFIER = "status_bar_height";

    private static final String DEFTYPE_DIMEN = "dimen";

    private static final String DEF_PACKAGE = "android";

    public static int convertToDp(int px) {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }

    public static int convertToPx(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static float getDeviceWidthInDp(Activity activity) {
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        return convertToDp(displayMetrics.widthPixels);
    }

    public static float getDeviceWidthInPixel(Activity activity) {
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        return displayMetrics.widthPixels;
    }

    public static float getDeviceHeightInDp(Activity activity) {
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        return convertToDp(displayMetrics.heightPixels);
    }

    public static float getDeviceHeightInPixel(Activity activity) {
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        return displayMetrics.heightPixels;
    }

    public static long getDeviceSizeInInches(Activity activity) {
        DisplayMetrics displayMetrics = getDisplayMetrics(activity);
        double width = (double) getDeviceWidthInPixel(activity) / (double) displayMetrics.xdpi;
        double height = (double) getDeviceHeightInPixel(activity) / (double) displayMetrics.ydpi;
        double x = Math.pow(width, 2);
        double y = Math.pow(height, 2);
        double sizeInInches = Math.sqrt(x + y);
        return Math.round(sizeInInches);
    }

    private static DisplayMetrics getDisplayMetrics(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return displayMetrics;
    }

    public static int getViewY(View view) {
        int[] locationScreen = new int[2];
        view.getLocationOnScreen(locationScreen);
        return locationScreen[INDEX_Y_POSISITON];
    }

    public static int getStatusBarHeight(Context context) {
        int resourceId = context.getResources().getIdentifier(STATUS_BAR_IDENTIFIER, DEFTYPE_DIMEN, DEF_PACKAGE);
        if (resourceId > 0) {
            return context.getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
