package de.xappo.presenterinjection;

import android.app.Activity;

import dagger.Component;

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
