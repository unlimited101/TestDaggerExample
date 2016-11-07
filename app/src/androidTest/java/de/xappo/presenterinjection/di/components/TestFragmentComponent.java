package de.xappo.presenterinjection.di.components;

import dagger.Component;
import de.xappo.presenterinjection.di.PerFragment;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.modules.FragmentModule;
import de.xappo.presenterinjection.di.modules.TestInteractorModule;
import de.xappo.presenterinjection.view.MainActivity;
import de.xappo.presenterinjection.view.MainActivityTest;
import de.xappo.presenterinjection.view.MainFragment;

/**
 * Created by knoppik on 28.10.16.
 */

@PerFragment
@Component(dependencies = TestApplicationComponent.class, modules = {ActivityModule.class, FragmentModule.class, TestInteractorModule.class})
public interface TestFragmentComponent extends FragmentComponent {
    void inject(MainActivityTest mainActivityTest);

}
