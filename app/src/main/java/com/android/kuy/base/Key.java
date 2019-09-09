package com.android.kuy.base;

import android.os.Parcelable;

import androidx.fragment.app.Fragment;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version Key, v 0.1 2019-09-10 01:56 by Abraham Ginting
 */
public interface Key extends Parcelable {

    String TAG = Key.class.getSimpleName();

    Fragment newFragment();

    String getFragmentTag();

}
