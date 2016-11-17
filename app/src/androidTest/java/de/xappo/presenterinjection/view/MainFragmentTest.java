package de.xappo.presenterinjection.view;

import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.test.InstrumentationRegistry;
import android.support.v4.app.Fragment;
import android.util.Log;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.di.TestFragmentComponentHolder;
import de.xappo.presenterinjection.di.components.DaggerTestFragmentComponent;
import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.components.TestFragmentComponent;
import de.xappo.presenterinjection.di.modules.TestFragmentModule;
import de.xappo.presenterinjection.runner.FragmentTestRule;

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
public class MainFragmentTest implements
        InjectsComponent<TestFragmentComponent>, TestFragmentComponentHolder.ComponentCreator {

    private static final String TAG = "MainFragmentTest";
    @Rule
    public FragmentTestRule<MainFragment> mFragmentTestRule = new FragmentTestRule<>(MainFragment.class);

    public AndroidApplication getApp() {
        return (AndroidApplication) InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }

    @Before
    public void setUp() throws Exception {
        TestFragmentComponentHolder.setCreator(this);


        setThingsUp();
    }

    private synchronized void setThingsUp() {

        mFragmentTestRule.launchActivity(null);

        MainFragment fragment = mFragmentTestRule.getFragment();

        ((HasComponent<FragmentComponent>) fragment).
                setComponent(TestFragmentComponentHolder.getComponent(fragment));

        injectFragmentGraph();

        waitForFragment(R.id.fragmentContainer, 5000);
    }

    @After
    public void tearDown() throws  Exception {
        TestFragmentComponentHolder.release();
        mFragmentTestRule = null;
    }

    @SuppressWarnings("unchecked")
    private void injectFragmentGraph() {
        ((InjectsComponent<TestFragmentComponent>) this).injectWith(TestFragmentComponentHolder.getComponent());
    }

    protected Fragment waitForFragment(@IdRes int id, int timeout) {
        long endTime = SystemClock.uptimeMillis() + timeout;
        while (SystemClock.uptimeMillis() <= endTime) {

            Fragment fragment = mFragmentTestRule.getActivity().getSupportFragmentManager().findFragmentById(id);
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