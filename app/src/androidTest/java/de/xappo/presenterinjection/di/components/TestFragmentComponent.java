package de.xappo.presenterinjection.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.TestFragmentModule;
import de.xappo.presenterinjection.di.scopes.PerFragment;
import de.xappo.presenterinjection.view.MainActivityTest;

/**
 * Created by knoppik on 27.10.16.
 */
@PerFragment
@Component(modules = TestFragmentModule.class)
public interface TestFragmentComponent extends FragmentComponent{
    void inject(MainActivityTest mainActivityTest);
}
