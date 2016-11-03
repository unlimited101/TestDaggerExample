package de.xappo.presenterinjection.di.modules;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.PerFragment;
import de.xappo.presenterinjection.interactor.MainInteractor;

/**
 * Created by knoppik on 03.11.16.
 */

@Module
public class InteractorModule {

    @Provides
    @PerFragment
    MainInteractor provideMainInteractor () {
        return new MainInteractor();
    }
}
