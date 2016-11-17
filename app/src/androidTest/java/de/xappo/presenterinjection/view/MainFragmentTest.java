package de.xappo.presenterinjection.view;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.base.FragmentTest;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.components.TestFragmentComponent;

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
public class MainFragmentTest extends FragmentTest implements
        InjectsComponent<TestFragmentComponent> {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    public AndroidApplication getApp() {
        return (AndroidApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Before
    public void setUp() throws Exception {
        mActivityRule.launchActivity(new Intent(getApp(), MainActivity.class));
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
    public void injectWith(final TestFragmentComponent component) {
        component.inject(this);
    }
}