package de.xappo.presenterinjection;

import android.app.Application;

/**
 * Created by knoppik on 27.10.16.
 */
public class AndroidApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
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

}
