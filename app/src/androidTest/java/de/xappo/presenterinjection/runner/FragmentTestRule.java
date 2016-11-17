package de.xappo.presenterinjection.runner;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import junit.framework.Assert;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.base.UITestActivity;

/**
 * Created by knoppik on 17.11.16.
 */

public class FragmentTestRule<F extends Fragment> extends ActivityTestRule<UITestActivity> {
    private final Class<F> mFragmentClass;
    private F mFragment;

    public FragmentTestRule(final Class<F> fragmentClass) {
        super(UITestActivity.class, true, false);
        mFragmentClass = fragmentClass;
    }

    @Override
    protected void beforeActivityLaunched() {
        super.beforeActivityLaunched();
        try {
            mFragment = mFragmentClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void afterActivityLaunched() {
        super.afterActivityLaunched();

        getActivity().runOnUiThread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void run() {

                //Instantiate and insert the fragment into the container layout
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();

                transaction.replace(R.id.fragmentContainer, mFragment);
                transaction.commit();

            }
        });
    }


    public F getFragment() {
        return mFragment;
    }
}
