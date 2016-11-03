package de.xappo.presenterinjection.di.components;

import dagger.Component;
import de.xappo.presenterinjection.di.PerFragment;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.modules.FragmentModule;
import de.xappo.presenterinjection.di.modules.InteractorModule;
import de.xappo.presenterinjection.view.MainActivity;
import de.xappo.presenterinjection.view.MainFragment;

/**
 * Created by knoppik on 28.10.16.
 */

@PerFragment
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FragmentModule.class, InteractorModule.class})
public interface FragmentComponent {
    void inject(MainFragment mainFragment);

    void inject(MainActivity mainActivity);
}
