package de.xappo.presenterinjection;

import android.support.v4.app.Fragment;

/**
 * Created by knoppik on 28.10.16.
 */
public class BaseFragment extends Fragment {

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }
}
