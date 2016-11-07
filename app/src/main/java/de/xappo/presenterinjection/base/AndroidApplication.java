package de.xappo.presenterinjection.base;

import android.app.Application;
import android.util.Log;

import de.xappo.presenterinjection.di.components.ApplicationComponent;
import de.xappo.presenterinjection.di.components.DaggerApplicationComponent;
import de.xappo.presenterinjection.di.modules.ApplicationModule;

/**
 * Created by knoppik on 27.10.16.
 */
public class AndroidApplication extends Application {

    private static final String TAG = "AndroidApplication";
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }

    public void setApplicationComponent(ApplicationComponent applicationComponent) {
        Log.w(TAG, "Only call this method to swap test doubles");
        this.applicationComponent = applicationComponent;
    }
}
