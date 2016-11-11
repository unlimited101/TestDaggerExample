package de.xappo.presenterinjection.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.BaseActivity;


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
