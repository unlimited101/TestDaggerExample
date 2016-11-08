package de.xappo.presenterinjection.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.modules.ApplicationModule;
import de.xappo.presenterinjection.di.scopes.PerActivity;

/**
 * Created by knoppik on 08.11.16.
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
}
