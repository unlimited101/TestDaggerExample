package de.xappo.presenterinjection.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.ApplicationModule;
import de.xappo.presenterinjection.view.MainFragment;

/**
 * Created by knoppik on 27.10.16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
// TODO: Comment this out for new approach because we have Singleton-Scope here and MainFragment injects MainPresenter which itself is in PerActivity scope!
//   void inject(MainFragment mainFragment);
}
