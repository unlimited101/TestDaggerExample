package de.xappo.presenterinjection.base;

import android.app.Activity;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;

/**
 * Created by knoppik on 11.11.16.
 */
public class IntentsTestRule<T extends Activity> extends ActivityTestRule<T> {

    public IntentsTestRule(Class<T> activityClass) {
        super(activityClass);
    }

    public IntentsTestRule(Class<T> activityClass, boolean initialTouchMode) {
        super(activityClass, initialTouchMode);
    }

    public IntentsTestRule(Class<T> activityClass, boolean initialTouchMode,
            boolean launchActivity) {
        super(activityClass, initialTouchMode, launchActivity);
    }

    @Override
    protected void afterActivityLaunched() {
        Intents.init();
        super.afterActivityLaunched();
    }

    @Override
    protected void afterActivityFinished() {
        super.afterActivityFinished();
        Intents.release();
    }

}