package com.android.kuy.utils;

import java.util.Locale;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version LocaleUtil, v 0.1 2019-09-10 04:12 by Abraham Ginting
 */
public class LocaleUtil {

    private static Locale idLocale;

    public static Locale getIndonesianLocale() {
        if (idLocale == null) {
            idLocale = new Locale("in", "ID");
        }
        return idLocale;
    }

    public static boolean isDefaultIndonesianLocale() {
        return "in".equals(getCurrentLocale().getLanguage());
    }

    public static Locale getCurrentLocale() {
        return Locale.getDefault();
    }

}
