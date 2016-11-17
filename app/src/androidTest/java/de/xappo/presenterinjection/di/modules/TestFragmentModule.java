package de.xappo.presenterinjection.di.modules;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.scopes.PerFragment;
import de.xappo.presenterinjection.fake.FakeMainInteractor;
import de.xappo.presenterinjection.interactor.MainInteractor;

/**
 * Created by knoppik on 08.11.16.
 */
@Module
public class TestFragmentModule {
    @Provides
    @PerFragment
    MainInteractor provideMainInteractor () {
        return new FakeMainInteractor();
    }
}
