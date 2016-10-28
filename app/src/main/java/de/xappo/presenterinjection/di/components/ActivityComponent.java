package de.xappo.presenterinjection.di.components;

import android.app.Activity;

import dagger.Component;
import de.xappo.presenterinjection.di.PerActivity;
import de.xappo.presenterinjection.di.modules.ActivityModule;

/**
 * Created by knoppik on 27.10.16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

}
