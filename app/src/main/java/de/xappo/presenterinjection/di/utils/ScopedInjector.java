package de.xappo.presenterinjection.di.utils;

/**
 * Created by knoppik on 11.11.16.
 */
public interface ScopedInjector<T> {
    void injectWith(T graph);
}
