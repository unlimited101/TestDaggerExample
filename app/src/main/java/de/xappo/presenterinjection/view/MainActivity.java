package de.xappo.presenterinjection.view;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.util.Log;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.base.UITestActivity;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.DaggerActivityComponent;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.utils.ComponentHolder;
import de.xappo.presenterinjection.di.utils.Injector;
import de.xappo.presenterinjection.di.utils.ScopedInjector;


public class MainActivity extends BaseActivity implements MainFragment.OnFragmentInteractionListener {


    private static final String TAG = "MainActivity";
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


    @Override
    public void onFragmentInteraction(final Uri uri) {

    }



}
