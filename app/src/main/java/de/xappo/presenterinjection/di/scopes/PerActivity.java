package de.xappo.presenterinjection.di.scopes;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by knoppik on 08.11.16.
 */

@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
