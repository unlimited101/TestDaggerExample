package de.xappo.presenterinjection.di;

import android.app.Activity;
import android.support.annotation.NonNull;

import de.xappo.presenterinjection.di.components.TestActivityComponent;

import static de.xappo.presenterinjection.base.Preconditions.checkState;

/**
 * Created by knoppik on 11.11.16.
 */

public class TestActivityComponentHolder {
    private static TestActivityComponent sComponent;
    private static ComponentCreator sCreator;

    public interface ComponentCreator {
        TestActivityComponent createComponent(Activity activity);
    }

    /**
     * Configures an ComponentCreator that is used to create an activity graph. This should be done _before_ the activity is
     * launched, usually in @Before or @BeforeClass.
     *
     * @param creator The creator
     */
    public static void setCreator(ComponentCreator creator) {
        sCreator = creator;
    }

    /**
     * Releases the static instances of our creator and graph. Useful for use in @After or @AfterClass.
     */
    public static void release() {
        sCreator = null;
        sComponent = null;
    }

    /**
     * Returns a previously instantiated {@link TestActivityComponent} or creates a new one using the registered {@link
     * ComponentCreator}
     *
     * @return The component
     * @throws IllegalStateException if no creator has been registered before
     */
    @NonNull
    public static TestActivityComponent getComponent(Activity activity) {
        if (sComponent == null) {
            checkState(sCreator != null, "no creator registered");
            sComponent = sCreator.createComponent(activity);
        }
        return sComponent;
    }


    /**
     * Returns true if a custom activity graph creator was configured for the current test run; false otherwise
     *
     * @return True or false
     */
    public static boolean hasComponentCreator() {
        return sCreator != null;
    }

    /**
     * Returns a previously instantiated {@link TestActivityComponent}.
     *
     * @return The component
     * @throws IllegalStateException if none has been instantiated
     */
    @NonNull
    public static TestActivityComponent getComponent() {
        checkState(sComponent != null, "no component created");
        return sComponent;
    }
}
