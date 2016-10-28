package de.xappo.presenterinjection;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by knoppik on 27.10.16.
 */
@Module
public class ActivityModule {
    private final Activity activity;

    public ActivityModule(Activity activity) {
        this.activity = activity;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity Activity activity() {
        return this.activity;
    }


}
