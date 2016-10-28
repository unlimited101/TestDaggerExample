package de.xappo.presenterinjection.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by knoppik on 27.10.16.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
