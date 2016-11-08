package de.xappo.presenterinjection.view;

import android.support.test.InstrumentationRegistry;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import android.support.test.rule.ActivityTestRule;
import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.di.components.DaggerTestActivityComponent;
import de.xappo.presenterinjection.di.components.DaggerTestApplicationComponent;
import de.xappo.presenterinjection.di.components.TestActivityComponent;
import de.xappo.presenterinjection.di.components.TestApplicationComponent;
import de.xappo.presenterinjection.di.modules.TestActivityModule;
import de.xappo.presenterinjection.di.modules.TestApplicationModule;
import static org.hamcrest.Matchers.containsString;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


/**
 * Created by knoppik on 03.11.16.
 */
public class MainActivityTest{

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule(MainActivity.class, true, false);

    private MainActivity mActivity;
    private TestActivityComponent mTestActivityComponent;

    private void initializeInjector() {
        mTestActivityComponent = DaggerTestActivityComponent.builder()
                .testActivityModule(new TestActivityModule())
                .build();

        mActivity.setActivityComponent(mTestActivityComponent);
        mTestActivityComponent.inject(this);
//        mTestApplicationComponent.inject(this);
    }

    public AndroidApplication getApp() {
        return (AndroidApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Before
    public void setUp() throws Exception {
        mActivityRule.launchActivity(null);
        mActivity = mActivityRule.getActivity();
        initializeInjector();


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

}