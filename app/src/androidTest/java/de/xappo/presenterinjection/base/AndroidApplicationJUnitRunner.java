package de.xappo.presenterinjection.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.test.runner.AndroidJUnitRunner;

import de.xappo.presenterinjection.di.TestActivityComponentHolder;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.utils.HasComponent;

/**
 * Created by knoppik on 11.11.16.
 */

public class AndroidApplicationJUnitRunner extends AndroidJUnitRunner {
    @Override
    public Application newApplication(ClassLoader classLoader, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(classLoader, TestAndroidApplication.class.getName(), context);
    }

    @Override
    public Activity newActivity(ClassLoader cl, String className, Intent intent)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        Activity activity = super.newActivity(cl, className, intent);
        return swapActivityGraph(activity);
    }

    @SuppressWarnings("unchecked")
    private Activity swapActivityGraph(Activity activity) {
        if (!(activity instanceof HasComponent) || !TestActivityComponentHolder.hasComponentCreator()) {
            return activity;
        }

        // In theory we should be able to use a Mockito spy here and stub out the graph delegation from
        // _all_ activities, however, this will lead to subtle crashes as we have to return the spy from
        // this method (i.e. a proxy) and this proxy will get the base context attached, not the actual
        // activity, see http://stackoverflow.com/questions/35495226/
        ((HasComponent<ActivityComponent>) activity).
                setComponent(TestActivityComponentHolder.getComponent(activity));
        return activity;
    }
}
