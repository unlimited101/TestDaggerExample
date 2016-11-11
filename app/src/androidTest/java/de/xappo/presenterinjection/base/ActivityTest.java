package de.xappo.presenterinjection.base;

import android.app.Activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import de.xappo.presenterinjection.di.TestActivityComponentHolder;
import de.xappo.presenterinjection.di.components.DaggerTestActivityComponent;
import de.xappo.presenterinjection.di.components.TestActivityComponent;
import de.xappo.presenterinjection.di.modules.TestActivityModule;
import de.xappo.presenterinjection.di.utils.InjectsComponent;

/**
 * Created by knoppik on 11.11.16.
 */
public class ActivityTest implements TestActivityComponentHolder.ComponentCreator {

    @Rule
    public IntentsTestRule<UITestActivity> activityRule = new IntentsTestRule<>(UITestActivity.class, true, false);

    private TestActivityComponent mTestActivityComponent;


    @Before
    public void createActivityGraphAndSetupView() {
        TestActivityComponentHolder.setCreator(this);
        UITestActivity activity = getActivity();
        injectActivityGraph();

    }

    @Before
    public void setUp() throws Exception{
        //createActivityGraphAndSetupView();

    }

    @After
    public void cleanup() {
        TestActivityComponentHolder.release();
    }



    private void injectActivityGraph() {

        ((InjectsComponent<TestActivityComponent>) this).injectWith(TestActivityComponentHolder.getComponent());
    }

    @Override
    public TestActivityComponent createComponent(final Activity activity) {
                mTestActivityComponent = DaggerTestActivityComponent.builder()
                .testActivityModule(new TestActivityModule())
                .build();

        return mTestActivityComponent;
    }

    /**
     * Returns the instantiated activity
     *
     * @return
     */
    protected UITestActivity getActivity() {
        UITestActivity activity = activityRule.getActivity();
        if (activity == null) {
            activity = activityRule.launchActivity(null);
        }
        return activity;
    }
}
