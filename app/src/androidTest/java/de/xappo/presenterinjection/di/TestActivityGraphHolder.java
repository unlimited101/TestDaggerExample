package de.xappo.presenterinjection.di;

import android.app.Activity;
import android.support.annotation.NonNull;

import de.xappo.presenterinjection.di.components.TestActivityComponent;

import static de.xappo.presenterinjection.base.Preconditions.checkState;

/**
 * Created by knoppik on 11.11.16.
 */

public class TestActivityGraphHolder {
    private static TestActivityComponent sComponent;
    private static GraphCreator sCreator;

    public interface GraphCreator {
        TestActivityComponent createGraph(Activity activity);
    }

    /**
     * Configures an GraphCreator that is used to create an activity graph. This should be done _before_ the activity is
     * launched, usually in @Before or @BeforeClass.
     *
     * @param creator The creator
     */
    public static void setCreator(GraphCreator creator) {
        sCreator = creator;
    }

    /**
     * Releases the static instances of our creator and graph. Useful for use in @After or @AfterClass.
     */
    public static void releaseCreatorAndGraph() {
        sCreator = null;
        sComponent = null;
    }

    /**
     * Returns a previously instantiated {@link TestActivityComponent} or creates a new one using the registered {@link
     * GraphCreator}
     *
     * @return The component
     * @throws IllegalStateException if no creator has been registered before
     */
    @NonNull
    public static TestActivityComponent getGraph(Activity activity) {
        if (sComponent == null) {
            checkState(sCreator != null, "no creator registered");
            sComponent = sCreator.createGraph(activity);
        }
        return sComponent;
    }


    /**
     * Returns true if a custom activity graph creator was configured for the current test run; false otherwise
     *
     * @return True or false
     */
    public static boolean hasGraphCreator() {
        return sCreator != null;
    }

    /**
     * Returns a previously instantiated {@link TestActivityComponent}.
     *
     * @return The component
     * @throws IllegalStateException if none has been instantiated
     */
    @NonNull
    public static TestActivityComponent getGraph() {
        checkState(sComponent != null, "no component created");
        return sComponent;
    }
}
