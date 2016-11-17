package de.xappo.presenterinjection.di.components;

import dagger.Component;
import de.xappo.presenterinjection.base.BaseFragment;
import de.xappo.presenterinjection.di.modules.FragmentModule;
import de.xappo.presenterinjection.di.scopes.PerFragment;
import de.xappo.presenterinjection.view.MainFragment;

/**
 * Created by knoppik on 17.11.16.
 */
@PerFragment
@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BaseFragment baseFragment);

    void inject(MainFragment mainFragment);
}
