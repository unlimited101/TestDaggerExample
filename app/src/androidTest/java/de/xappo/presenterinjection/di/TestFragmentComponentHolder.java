package de.xappo.presenterinjection.di;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import de.xappo.presenterinjection.di.components.TestFragmentComponent;

import static de.xappo.presenterinjection.utils.Preconditions.checkState;

/**
 * Created by knoppik on 11.11.16.
 */

/**
 * Because neither the Activity nor the ActivityTest can hold the TestActivityComponent (due to
 * runtime order problems we need to hold it statically
 **/
public class TestFragmentComponentHolder {
    private static TestFragmentComponent sComponent;
    private static ComponentCreator sCreator;

    public interface ComponentCreator {
        TestFragmentComponent createComponent(Fragment fragment);
    }

    /**
     * Configures an ComponentCreator that is used to create an activity graph. Shall be done
     * _before_ the activity is launched, hence in @Before.
     *
     * @param creator The creator
     */
    public static void setCreator(ComponentCreator creator) {
        sCreator = creator;
    }

    /**
     * Releases the static instances of our creator and graph. Shall be used in @After.
     */
    public static void release() {
        sCreator = null;
        sComponent = null;
    }

    /**
     * Returns the {@link TestFragmentComponent} or creates a new one using the registered {@link
     * ComponentCreator}
     *
     * @throws IllegalStateException if no creator has been registered before
     */
    @NonNull
    public static TestFragmentComponent getComponent(Fragment fragment) {
        if (sComponent == null) {
            checkState(sCreator != null, "no creator registered");
            sComponent = sCreator.createComponent(fragment);
        }
        return sComponent;
    }


    /**
     * Returns true if a custom activity component creator was configured for the current test run,
     * false otherwise
     */
    public static boolean hasComponentCreator() {
        return sCreator != null;
    }

    /**
     * Returns a previously instantiated {@link TestFragmentComponent}.
     *
     * @throws IllegalStateException if none has been instantiated
     */
    @NonNull
    public static TestFragmentComponent getComponent() {
        checkState(sComponent != null, "no component created");
        return sComponent;
    }
}
