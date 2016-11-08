package de.xappo.presenterinjection.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.interactor.MainInteractor;

/**
 * Created by knoppik on 08.11.16.
 */
@Module
public class ActivityModule {
    @Provides
    @PerActivity
    MainInteractor provideMainInteractor () {
        return new MainInteractor();
    }
}
