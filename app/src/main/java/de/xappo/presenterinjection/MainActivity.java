package de.xappo.presenterinjection;

import android.net.Uri;
import android.os.Bundle;


public class MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener, HasComponent<ActivityComponent>{

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
