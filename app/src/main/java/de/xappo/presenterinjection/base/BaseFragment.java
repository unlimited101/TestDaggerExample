package de.xappo.presenterinjection.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;

import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.utils.InjectorUtils;

/**
 * Created by knoppik on 28.10.16.
 */
public class BaseFragment extends Fragment implements
        HasComponent<FragmentComponent>, InjectsComponent<FragmentComponent> {

    private static final String TAG = "BaseFragment";
    private FragmentComponent fragmentComponent;

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectorUtils.setUp(this);
    }

    @Override
    public FragmentComponent getComponent() {
        return fragmentComponent;
    }

    @Override
    public void setComponent(final FragmentComponent component) {
        Log.w(TAG, "injectDagger Only call this method to swap test doubles");
        this.fragmentComponent = component;
    }

    @Override
    public void injectWith(final FragmentComponent component) {
        component.inject(this);

    }
}
