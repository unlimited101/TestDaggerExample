package de.xappo.presenterinjection.di.utils;

import de.xappo.presenterinjection.di.components.ActivityComponent;

/**
 * Created by knoppik on 11.11.16.
 */
public interface ComponentHolder<T> {

    T getComponent();

    void setComponent(T component);

}
