package de.xappo.presenterinjection.di.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.fake.FakeMainInteractor;
import de.xappo.presenterinjection.interactor.MainInteractor;

/**
 * Created by knoppik on 03.11.16.
 */

@Module
public class TestApplicationModule {
    private final AndroidApplication application;

    public TestApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    MainInteractor provideMainInteractor () {
        return new FakeMainInteractor();
    }

}
