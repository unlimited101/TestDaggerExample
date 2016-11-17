package de.xappo.presenterinjection.view;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.v4.app.Fragment;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.base.FragmentTest;
import de.xappo.presenterinjection.di.TestActivityComponentHolder;
import de.xappo.presenterinjection.di.TestFragmentComponentHolder;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.DaggerTestFragmentComponent;
import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.components.TestFragmentComponent;
import de.xappo.presenterinjection.di.modules.TestFragmentModule;

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
        InjectsComponent<TestFragmentComponent>, TestFragmentComponentHolder.ComponentCreator {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    public AndroidApplication getApp() {
        return (AndroidApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Before
    public void setUp() throws Exception {
        TestFragmentComponentHolder.setCreator(this);

        mActivityRule.launchActivity(new Intent(getApp(), MainActivity.class));
        MainFragment fragment = (MainFragment) waitForFragment(R.id.fragmentContainer, 5500);






        ((HasComponent<FragmentComponent>) fragment).
                setComponent(TestFragmentComponentHolder.getComponent(fragment));

        injectFragmentGraph();

    }

    @SuppressWarnings("unchecked")
    private void injectFragmentGraph() {
        ((InjectsComponent<TestFragmentComponent>) this).injectWith(TestFragmentComponentHolder.getComponent());
    }

    protected Fragment waitForFragment(@IdRes int id, int timeout) {
        long endTime = SystemClock.uptimeMillis() + timeout;
        while (SystemClock.uptimeMillis() <= endTime) {

            Fragment fragment = mActivityRule.getActivity().getSupportFragmentManager().findFragmentById(id);
            if (fragment != null) {
                return fragment;
            }
        }
        return null;
    }

    @Override
    public TestFragmentComponent createComponent(final Fragment fragment) {
        return DaggerTestFragmentComponent.builder()
                .testFragmentModule(new TestFragmentModule())
                .build();
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