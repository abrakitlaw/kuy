package com.android.kuy.base;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseKey, v 0.1 2019-09-10 01:55 by Abraham Ginting
 */
public abstract class BaseKey implements Key {

    @Override
    public Fragment newFragment() {
        Fragment fragment = createFragment();

        Bundle bundle = fragment.getArguments();
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putParcelable(TAG, this);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public String getFragmentTag() {
        return toString();
    }

    protected abstract Fragment createFragment();
}
