package de.xappo.presenterinjection.di.components;

import dagger.Component;
import de.xappo.presenterinjection.base.BaseActivity;
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

    void inject(BaseActivity baseActivity);

    void inject(MainFragment mainFragment);
}
