package de.xappo.presenterinjection.di.components;

import javax.inject.Singleton;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.view.MainActivity;
import de.xappo.presenterinjection.view.MainFragment;

/**
 * Created by knoppik on 08.11.16.
 */
@PerActivity
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
    // TODO: Comment this out for making the new approach working
//    void inject(MainFragment mainFragment);
}
