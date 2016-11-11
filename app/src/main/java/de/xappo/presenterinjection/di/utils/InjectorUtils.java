package de.xappo.presenterinjection.di.utils;

import android.app.Activity;
import android.content.Context;

import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.ApplicationComponent;
import de.xappo.presenterinjection.di.components.DaggerActivityComponent;
import de.xappo.presenterinjection.di.modules.ActivityModule;

/**
 * Created by knoppik on 11.11.16.
 */
public final class InjectorUtils {

    public static void setUp(Activity activity) {
        setupWithContext(activity.getApplicationContext(), activity);
    }

    private static void setupWithContext(final Context context, final Activity activity) {
        //TODO: Optional: get scope from test class annotation

        AndroidApplication app = getApplication(context);
        ApplicationComponent applicationComponent = app.getApplicationComponent();

        //TODO: Optional: Call injectWith for associated scope

        ActivityComponent activityComponent = getOrCreateActivityComponent(activity);
        //TODO: Optional: Check if component can be injected by interface implementation check


        //TODO: Optional: Return if successful

        ((InjectsComponent<ActivityComponent>) activity).injectWith(activityComponent);

    }


    private static ActivityComponent getOrCreateActivityComponent(final Activity activity) {
        HasComponent<ActivityComponent> holder = (HasComponent<ActivityComponent>) activity;
        if (holder.getComponent() == null) {
            holder.setComponent(createActivityComponent(activity));
        }
        return holder.getComponent();
    }

    private static ActivityComponent createActivityComponent(final Activity activity) {
        return DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule())
                .build();
    }

    private static AndroidApplication getApplication(Context context) {
        return (AndroidApplication) context.getApplicationContext();
    }

}
