package de.xappo.presenterinjection.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;


import android.util.Log;
import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.di.HasComponent;


import de.xappo.presenterinjection.di.components.DaggerFragmentComponent;
import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.modules.FragmentModule;
import de.xappo.presenterinjection.di.modules.InteractorModule;


public class MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener,
        HasComponent<FragmentComponent> {


    private static final String TAG = "MainActivity";
    private FragmentComponent fragmentComponent;
    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            currentFragment = new MainFragment();
            addFragment(R.id.fragmentContainer, currentFragment);
        }

    }


    private void initializeInjector() {
        fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .fragmentModule(getFragmentModule())
                .interactorModule(new InteractorModule())
                .build();
    }

    @Override
    protected void onActivitySetup() {

        Log.i(TAG, "injectDagger initializeInjector()");

        initializeInjector();
        fragmentComponent.inject(this);

    }

    @Override
    public void setFragmentComponent(final FragmentComponent fragmentComponent) {
        Log.i(TAG, "injectDagger setFragmentComponent()");

        super.setFragmentComponent(fragmentComponent);
        this.fragmentComponent = fragmentComponent;
    }

    @Override
    public void onFragmentInteraction(final Uri uri) {

    }

    @Override public FragmentComponent getComponent() {
        return fragmentComponent;
    }


    public FragmentModule getFragmentModule() {
        return new FragmentModule(currentFragment);
    }
}
