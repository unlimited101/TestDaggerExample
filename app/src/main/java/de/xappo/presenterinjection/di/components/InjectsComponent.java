package de.xappo.presenterinjection.di.components;

/**
 * Created by knoppik on 11.11.16.
 */
public interface InjectsComponent<T> {
    void injectWith(T component);
}
