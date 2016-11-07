package de.xappo.presenterinjection.view;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.di.components.DaggerTestApplicationComponent;
import de.xappo.presenterinjection.di.components.DaggerTestFragmentComponent;
import de.xappo.presenterinjection.di.components.TestApplicationComponent;
import de.xappo.presenterinjection.di.components.TestFragmentComponent;
import de.xappo.presenterinjection.di.modules.ApplicationModule;
import de.xappo.presenterinjection.di.modules.TestInteractorModule;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.Matchers.containsString;


/**
 * Created by knoppik on 03.11.16.
 */
public class MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class, true, true);

    private MainActivity mActivity;
    private TestApplicationComponent mTestApplicationComponent;
    private TestFragmentComponent mTestFragmentComponent;

    private void initializeInjector() {
        mTestApplicationComponent = DaggerTestApplicationComponent.builder()
                .applicationModule(new ApplicationModule(getApp()))
                .build();

        getApp().setApplicationComponent(mTestApplicationComponent);

        mTestFragmentComponent = DaggerTestFragmentComponent.builder()
                .testApplicationComponent(mTestApplicationComponent)
                .activityModule(mActivity.getActivityModule())
                .testInteractorModule(new TestInteractorModule())
                .build();

        mActivity.setFragmentComponent(mTestFragmentComponent);

        mTestApplicationComponent.inject(this);
        mTestFragmentComponent.inject(this);

    }

    public AndroidApplication getApp() {
        return (AndroidApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Before
    public void setUp() throws Exception {
        mActivity = mActivityRule.getActivity();
        initializeInjector();
    }

    @Test
    public void testMainFragmentLoaded() throws Exception {
        mActivity = mActivityRule.getActivity();
        assertTrue(mActivity.getCurrentFragment() instanceof MainFragment);
    }

    @Test
    public void testOnClick() throws Exception {
        onView(withId(R.id.edittext)).perform(typeText("John"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textview_greeting)).check(matches(withText(containsString("Hello John"))));
    }

}