package de.xappo.presenterinjection.di.components;

import android.app.Activity;

import dagger.Component;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.view.MainActivity;
import de.xappo.presenterinjection.view.MainFragment;
import de.xappo.presenterinjection.di.PerActivity;

/**
 * Created by knoppik on 27.10.16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();

    void inject(MainActivity mainActivity);
    void inject(MainFragment mainFragment);
}
