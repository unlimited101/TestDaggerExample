package de.xappo.presenterinjection.base;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v4.app.Fragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import de.xappo.presenterinjection.di.TestFragmentComponentHolder;
import de.xappo.presenterinjection.di.components.DaggerTestFragmentComponent;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.components.TestFragmentComponent;
import de.xappo.presenterinjection.di.modules.TestFragmentModule;

/**
 * Created by knoppik on 17.11.16.
 */
public class FragmentTest implements TestFragmentComponentHolder.ComponentCreator {

    @Rule
    public IntentsTestRule<UITestActivity> activityRule = new IntentsTestRule<>(UITestActivity.class, true, false);

    @Before
    public void createFragmentGraphAndSetupView() {
        TestFragmentComponentHolder.setCreator(this);
        UITestActivity activity = getActivity();
        injectFragmentGraph();
    }
    @After
    public void cleanUp() {
        TestFragmentComponentHolder.release();
    }

    @SuppressWarnings("unchecked")
    private void injectFragmentGraph() {
        ((InjectsComponent<TestFragmentComponent>) this).injectWith(TestFragmentComponentHolder.getComponent());
    }


    @Override
    public TestFragmentComponent createComponent(final Fragment fragment) {
        return DaggerTestFragmentComponent.builder()
                .testFragmentModule(new TestFragmentModule())
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
