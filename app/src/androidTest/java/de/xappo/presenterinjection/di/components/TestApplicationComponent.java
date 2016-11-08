package de.xappo.presenterinjection.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.TestApplicationModule;
import de.xappo.presenterinjection.view.MainActivityTest;

/**
 * Created by knoppik on 27.10.16.
 */
@Singleton
@Component(modules = TestApplicationModule.class)
public interface TestApplicationComponent extends ApplicationComponent{
    void inject(MainActivityTest mainActivityTest);
}
