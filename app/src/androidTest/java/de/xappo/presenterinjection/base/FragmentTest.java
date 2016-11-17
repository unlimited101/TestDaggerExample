package de.xappo.presenterinjection.base;

import android.os.SystemClock;
import android.support.annotation.IdRes;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.v4.app.Fragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

import java.lang.reflect.ParameterizedType;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.di.TestFragmentComponentHolder;
import de.xappo.presenterinjection.di.components.DaggerTestFragmentComponent;
import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.components.TestFragmentComponent;
import de.xappo.presenterinjection.di.modules.TestFragmentModule;
import de.xappo.presenterinjection.runner.FragmentTestRule;

/**
 * Created by knoppik on 17.11.16.
 */
public class FragmentTest<F extends Fragment> implements TestFragmentComponentHolder.ComponentCreator  {

    @Rule
    public IntentsTestRule<UITestActivity> activityRule = new IntentsTestRule<>(UITestActivity.class, true, false);

    private Class<F> myClass;

    @Rule
    public FragmentTestRule<F> mFragmentTestRule;

    public FragmentTest() {
        this.myClass = (Class<F>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        mFragmentTestRule = new FragmentTestRule<>(myClass);
    }

    @Before
    public void setUp() {
        TestFragmentComponentHolder.setCreator(this);

        mFragmentTestRule.launchActivity(null);

        F fragment = mFragmentTestRule.getFragment();

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

    @After
    public void cleanUp() {
        TestFragmentComponentHolder.release();
    }

    @Override
    public TestFragmentComponent createComponent(final Fragment fragment) {
        return DaggerTestFragmentComponent.builder()
                .testFragmentModule(new TestFragmentModule())
                .build();
    }
}
