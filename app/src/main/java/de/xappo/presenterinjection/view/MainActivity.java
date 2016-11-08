package de.xappo.presenterinjection.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.util.Log;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.DaggerActivityComponent;
import de.xappo.presenterinjection.di.modules.ActivityModule;


public class MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener {


    private static final String TAG = "MainActivity";
    private Fragment currentFragment;
    private ActivityComponent activityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeInjector();


        if (savedInstanceState == null) {
            currentFragment = new MainFragment();
            addFragment(R.id.fragmentContainer, currentFragment);
        }


    }

    private void initializeInjector() {
        Log.i(TAG, "injectDagger initializeInjector()");

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule())
                .build();
    }

    @Override
    public void onFragmentInteraction(final Uri uri) {

    }

    ActivityComponent getActivityComponent() {
        return activityComponent;
    }

    @VisibleForTesting
    public void setActivityComponent(ActivityComponent activityComponent) {
        Log.w(TAG, "injectDagger Only call this method to swap test doubles");
        this.activityComponent = activityComponent;
    }
}
