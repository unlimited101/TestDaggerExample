package de.xappo.presenterinjection.di.components;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.TestActivityModule;
import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.view.MainActivityTest;

/**
 * Created by knoppik on 08.11.16.
 */
@PerActivity
@Component(modules = TestActivityModule.class)
public interface TestActivityComponent extends ActivityComponent {
    void inject(MainActivityTest mainActivityTest);
}
