package de.xappo.presenterinjection.view;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.ActivityTest;
import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.di.components.TestActivityComponent;
import de.xappo.presenterinjection.di.utils.InjectsComponent;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.containsString;


/**
 * Created by knoppik on 03.11.16.
 */
public class MainActivityTest extends ActivityTest implements
        InjectsComponent<TestActivityComponent> {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class, true, false);

    private MainActivity mActivity;
    private TestActivityComponent mTestActivityComponent;

    // TODO: That approach works
//    private TestApplicationComponent mTestApplicationComponent;
//
//    private void initializeInjector() {
//        mTestApplicationComponent = DaggerTestApplicationComponent.builder()
//                .testApplicationModule(new TestApplicationModule(getApp()))
//                .build();
//
//        getApp().setApplicationComponent(mTestApplicationComponent);
//        mTestApplicationComponent.inject(this);
//    }

//    // TODO: This approach does NOT work because mActivity.setActivityComponent() is called after MainInteractor has already been injected!
//    private void initializeInjector() {
//        mTestActivityComponent = DaggerTestActivityComponent.builder()
//                .testActivityModule(new TestActivityModule())
//                .build();
//
//        mActivity.setActivityComponent(mTestActivityComponent);
//        mTestActivityComponent.inject(this);
//    }

    public AndroidApplication getApp() {
        return (AndroidApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
    // TODO: That approach works

//    @Before
//    public void setUp() throws Exception {
//
//        initializeInjector();
//        activityRule.launchActivity(null);
//        mActivity = activityRule.getActivity();
//    }

    // TODO: That approach does not works because mActivity.setActivityComponent() is called after MainInteractor has already been injected!
    @Before
    public void setUp() throws Exception {
//        super.setUp();
        mActivityRule.launchActivity(new Intent(getApp(), MainActivity.class));
        mActivity = mActivityRule.getActivity();

    }


    @Test
    public void testOnClick_Fake() throws Exception {
        onView(withId(R.id.edittext)).perform(typeText("John"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textview_greeting)).check(matches(withText(containsString("Hello Fake"))));
    }

    @Test
    public void testOnClick_Real() throws Exception {
        onView(withId(R.id.edittext)).perform(typeText("John"));
        onView(withId(R.id.button)).perform(click());
        onView(withId(R.id.textview_greeting)).check(matches(withText(containsString("Hello John"))));
    }

    @Override
    public void injectWith(final TestActivityComponent component) {
        component.inject(this);
    }
}