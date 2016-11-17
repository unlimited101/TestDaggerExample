package de.xappo.presenterinjection.runner;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.test.runner.AndroidJUnitRunner;
import android.support.v4.app.Fragment;

import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.base.TestAndroidApplication;
import de.xappo.presenterinjection.di.TestActivityComponentHolder;
import de.xappo.presenterinjection.di.TestFragmentComponentHolder;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.components.TestFragmentComponent;

/**
 * Created by knoppik on 11.11.16.
 */

/**
 * Own JUnit runner for intercepting the ActivityComponent injection and swapping the
 * ActivityComponent with the TestActivityComponent
 */
public class AndroidApplicationJUnitRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader classLoader, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(classLoader, TestAndroidApplication.class.getName(), context);
    }

    @Override
    public Activity newActivity(ClassLoader classLoader, String className, Intent intent)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Activity activity = super.newActivity(classLoader, className, intent);
        return swapActivityGraph(activity);
    }

    @SuppressWarnings("unchecked")
    private Activity swapActivityGraph(Activity activity) {
        if (!(activity instanceof HasComponent) || !TestActivityComponentHolder.hasComponentCreator()) {
            return activity;
        }

        ((HasComponent<ActivityComponent>) activity).
                setComponent(TestActivityComponentHolder.getComponent(activity));

        return activity;
    }
}
