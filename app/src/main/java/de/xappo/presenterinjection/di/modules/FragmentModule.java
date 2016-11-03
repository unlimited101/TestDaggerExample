package de.xappo.presenterinjection.di.modules;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import de.xappo.presenterinjection.di.PerFragment;

/**
 * Created by knoppik on 28.10.16.
 */
@Module
public class FragmentModule {
    private final Fragment fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = fragment;
    }

    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerFragment
    Fragment fragment() {
        return this.fragment;
    }

}
