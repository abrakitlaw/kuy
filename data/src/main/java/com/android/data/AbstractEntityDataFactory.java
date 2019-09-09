package com.android.data;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version AbstractEntityDataFactory, v 0.1 2019-09-10 02:48 by Abraham Ginting
 */
public abstract class AbstractEntityDataFactory<T> {

    public abstract T createData(@Source String source);
}
