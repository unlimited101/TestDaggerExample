package de.xappo.presenterinjection;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by knoppik on 27.10.16.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
}
