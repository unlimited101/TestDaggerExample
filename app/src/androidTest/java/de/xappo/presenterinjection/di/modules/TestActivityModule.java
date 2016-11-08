package de.xappo.presenterinjection.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.fake.FakeMainInteractor;
import de.xappo.presenterinjection.interactor.MainInteractor;
import de.xappo.presenterinjection.interactor.SomeInteractor;

/**
 * Created by knoppik on 08.11.16.
 */
@Module
public class TestActivityModule {
    @Provides
    @PerActivity
    SomeInteractor provideSomeInteractor () {
        return new SomeInteractor("test some interactor");
    }

    @Provides
    @PerActivity
    MainInteractor provideMainInteractor () {
        return new FakeMainInteractor();
    }
}
