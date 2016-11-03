package de.xappo.presenterinjection.di.modules;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.PerFragment;
import de.xappo.presenterinjection.fake.FakeMainInteractor;
import de.xappo.presenterinjection.interactor.MainInteractor;

import static org.mockito.Mockito.mock;

/**
 * Created by knoppik on 03.11.16.
 */

@Module
public class TestInteractorModule {

    @Provides
    @PerFragment
    FakeMainInteractor provideMainInteractor () {
        return new FakeMainInteractor();
    }
}
