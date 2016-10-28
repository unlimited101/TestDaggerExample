package de.xappo.presenterinjection.view;

import android.net.Uri;
import android.os.Bundle;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.di.HasComponent;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.DaggerActivityComponent;


public class MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener,
        HasComponent<ActivityComponent> {

    private ActivityComponent activityComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, new MainFragment());
        }

    }


    private void initializeInjector() {
        this.activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onActivitySetup() {
        this.initializeInjector();
        activityComponent.inject(this);

    }

    @Override
    public void onFragmentInteraction(final Uri uri) {

    }

    @Override public ActivityComponent getComponent() {
        return activityComponent;
    }
}
