package com.android.data.base;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version BaseMapper, v 0.1 2019-09-10 03:02 by Abraham Ginting
 */
public abstract class BaseMapper<T, R> {

    public R apply(T sourceItem) {
        return map(sourceItem);
    }

    protected abstract R map(T oldItem);
}
