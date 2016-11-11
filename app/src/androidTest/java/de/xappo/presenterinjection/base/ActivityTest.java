package de.xappo.presenterinjection.base;

import android.app.Activity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import de.xappo.presenterinjection.di.TestActivityGraphHolder;
import de.xappo.presenterinjection.di.components.DaggerTestActivityComponent;
import de.xappo.presenterinjection.di.components.TestActivityComponent;
import de.xappo.presenterinjection.di.modules.TestActivityModule;
import de.xappo.presenterinjection.di.utils.ScopedInjector;

/**
 * Created by knoppik on 11.11.16.
 */
public class ActivityTest implements TestActivityGraphHolder.GraphCreator{

    @Rule
    public IntentsTestRule<UITestActivity> activityRule = new IntentsTestRule<>(UITestActivity.class, true, false);

    private TestActivityComponent mTestActivityComponent;


    @Before
    public void createActivityGraphAndSetupView() {
        TestActivityGraphHolder.setCreator(this);
        UITestActivity activity = getActivity();
        injectActivityGraph();

    }

    @Before
    public void setUp() throws Exception{
        //createActivityGraphAndSetupView();

    }

    @After
    public void cleanup() {
        TestActivityGraphHolder.releaseCreatorAndGraph();
    }



    private void injectActivityGraph() {

        ((ScopedInjector<TestActivityComponent>) this).injectWith(TestActivityGraphHolder.getGraph());
    }

    @Override
    public TestActivityComponent createGraph(final Activity activity) {
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
