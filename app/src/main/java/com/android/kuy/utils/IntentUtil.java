package com.android.kuy.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version IntentUtil, v 0.1 2019-09-10 04:11 by Abraham Ginting
 */
public class IntentUtil {

    public static Intent getImageIntent(Uri uri) {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.putExtra(Intent.EXTRA_STREAM, uri);
        sharingIntent.setType("image/*");
        sharingIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        return sharingIntent;
    }

    public static Intent getTextIntent(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        return shareIntent;
    }

    public static boolean isIntentPackageAvailable(Context context, String packageName) {
        PackageManager pm = context.getPackageManager();
        try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
