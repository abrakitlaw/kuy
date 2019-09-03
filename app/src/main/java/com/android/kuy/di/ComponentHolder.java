package com.android.kuy.di;

import com.android.kuy.di.component.ApplicationComponent;

/**
 * @author Abraham Ginting (abraham.ginting@dana.id)
 * @version ComponentHolder, v 0.1 2019-09-04 04:24 by Abraham Ginting
 */
public class ComponentHolder {

    private static ComponentHolder componentHolder;

    private final ApplicationComponent applicationComponent;

    public ComponentHolder(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }

    public static void set(ApplicationComponent applicationComponent) {
        if (componentHolder == null) {
            componentHolder = new ComponentHolder(applicationComponent);
        }
    }

    public static ApplicationComponent provide() {
        if (componentHolder == null) throw new IllegalStateException(
            "Application components needs to be set in Application");

        return componentHolder.applicationComponent;
    }
}
