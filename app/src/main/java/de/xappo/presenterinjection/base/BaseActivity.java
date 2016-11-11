package de.xappo.presenterinjection.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.ApplicationComponent;
import de.xappo.presenterinjection.di.utils.ComponentHolder;
import de.xappo.presenterinjection.di.utils.Injector;
import de.xappo.presenterinjection.di.utils.ScopedInjector;

/**
 * Created by knoppik on 27.10.16.
 */
public abstract class BaseActivity extends AppCompatActivity implements
        ComponentHolder<ActivityComponent>, ScopedInjector<ActivityComponent> {

    private static final String TAG = "BaseActivity";

    private ActivityComponent activityComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.setUp(this);


    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    public Fragment getCurrentFragment() {
        return getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
    }

    @Override
    public ActivityComponent getComponent() {
        return activityComponent;
    }

    @VisibleForTesting
    @Override
    public void setComponent(final ActivityComponent component) {
        Log.w(TAG, "injectDagger Only call this method to swap test doubles");
        this.activityComponent = component;
    }

    @Override
    public void injectWith(final ActivityComponent graph) {
        graph.inject(this);
    }

}
