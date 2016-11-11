package de.xappo.presenterinjection.di.components;

import de.xappo.presenterinjection.di.components.ActivityComponent;

/**
 * Created by knoppik on 11.11.16.
 */
public interface HasComponent<T> {

    T getComponent();

    void setComponent(T component);

}