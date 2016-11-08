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
    // TODO: Comment this out for switching back to the old approach
    void inject(MainFragment mainFragment);
    // TODO: Leave that for witching to the new approach
    void inject(MainActivity mainActivity);
}
