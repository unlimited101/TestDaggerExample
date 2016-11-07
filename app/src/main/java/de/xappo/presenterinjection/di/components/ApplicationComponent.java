package de.xappo.presenterinjection.di.components;

import de.xappo.presenterinjection.view.MainFragment;
import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.ApplicationModule;
import de.xappo.presenterinjection.base.BaseActivity;

/**
 * Created by knoppik on 27.10.16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(MainFragment mainFragment);
}
