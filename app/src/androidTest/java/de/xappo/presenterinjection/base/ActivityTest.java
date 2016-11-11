package de.xappo.presenterinjection.base;

import android.app.Activity;
import android.support.test.espresso.intent.rule.IntentsTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import de.xappo.presenterinjection.di.TestActivityComponentHolder;
import de.xappo.presenterinjection.di.components.DaggerTestActivityComponent;
import de.xappo.presenterinjection.di.components.TestActivityComponent;
import de.xappo.presenterinjection.di.modules.TestActivityModule;
import de.xappo.presenterinjection.di.components.InjectsComponent;

/**
 * Created by knoppik on 11.11.16.
 */
public class ActivityTest implements TestActivityComponentHolder.ComponentCreator {

    @Rule
    public IntentsTestRule<UITestActivity> activityRule = new IntentsTestRule<>(UITestActivity.class, true, false);

    @Before
    public void createActivityGraphAndSetupView() {
        TestActivityComponentHolder.setCreator(this);
        UITestActivity activity = getActivity();
        injectActivityGraph();
    }

    @After
    public void cleanUp() {
        TestActivityComponentHolder.release();
    }


    @SuppressWarnings("unchecked")
    private void injectActivityGraph() {
        ((InjectsComponent<TestActivityComponent>) this).injectWith(TestActivityComponentHolder.getComponent());
    }

    @Override
    public TestActivityComponent createComponent(final Activity activity) {
        return DaggerTestActivityComponent.builder()
                .testActivityModule(new TestActivityModule())
                .build();
    }

    private UITestActivity getActivity() {
        UITestActivity activity = activityRule.getActivity();
        if (activity == null) {
            activity = activityRule.launchActivity(null);
        }
        return activity;
    }
}
