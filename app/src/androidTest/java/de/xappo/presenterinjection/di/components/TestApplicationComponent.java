package de.xappo.presenterinjection.di.components;

import de.xappo.presenterinjection.di.modules.TestApplicationModule;
import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.di.modules.ApplicationModule;
import de.xappo.presenterinjection.view.MainActivityTest;

/**
 * Created by knoppik on 27.10.16.
 */
@Singleton
@Component(modules = TestApplicationModule.class)
public interface TestApplicationComponent extends ApplicationComponent{
    void inject(MainActivityTest mainActivityTest);
}
