package de.xappo.presenterinjection.di.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import de.xappo.presenterinjection.base.AndroidApplication;
import de.xappo.presenterinjection.base.BaseActivity;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.ApplicationComponent;
import de.xappo.presenterinjection.di.components.DaggerActivityComponent;
import de.xappo.presenterinjection.di.components.DaggerFragmentComponent;
import de.xappo.presenterinjection.di.components.FragmentComponent;
import de.xappo.presenterinjection.di.components.HasComponent;
import de.xappo.presenterinjection.di.components.InjectsComponent;
import de.xappo.presenterinjection.di.modules.ActivityModule;
import de.xappo.presenterinjection.di.modules.FragmentModule;

/**
 * Created by knoppik on 11.11.16.
 */
public final class InjectorUtils {

    //TODO: Optional: Implement for Fragment and View
    /**
     * Sets up the activity component and injects it
     * @param activity
     */
    public static void setUp(Activity activity) {
        setupWithContext(activity.getApplicationContext(), activity);
    }

    public static void setUp(Fragment fragment) {
        setupWithContext(fragment.getContext().getApplicationContext(), fragment);
    }

    //TODO: Optional: Support everything instead of Activity (Object instead)

    @SuppressWarnings("unchecked")
    private static void setupWithContext(final Context context, final Activity activity) {
        //TODO: Optional: get scope from test class annotation

        AndroidApplication app = getApplication(context);
        ApplicationComponent applicationComponent = app.getApplicationComponent();

        //TODO: Optional: check scope

        //TODO: Optional: Call injectComponent for associated scope

        ActivityComponent activityComponent = getOrCreateActivityComponent(activity);
        //TODO: Optional: Check if component can be injected by interface implementation check


        //TODO: Optional: Return if successful

        ((InjectsComponent<ActivityComponent>) activity).injectWith(activityComponent);

    }

    @SuppressWarnings("unchecked")
    private static void setupWithContext(final Context context, final Fragment fragment) {
        //TODO: Optional: get scope from test class annotation

        AndroidApplication app = getApplication(context);
        ApplicationComponent applicationComponent = app.getApplicationComponent();

        //TODO: Optional: check scope

        //TODO: Optional: Call injectComponent for associated scope

        ActivityComponent activityComponent = ((BaseActivity) fragment.getActivity()).getComponent();
        //TODO: Optional: Check if component can be injected by interface implementation check


        //TODO: Optional: Return if successful

        FragmentComponent fragmentComponent = getOrCreateFragmentComponent(fragment);

        ((InjectsComponent<FragmentComponent>) fragment).injectWith(fragmentComponent);

    }




    @SuppressWarnings("unchecked")
    private static ActivityComponent getOrCreateActivityComponent(final Activity activity) {
        HasComponent<ActivityComponent> holder = (HasComponent<ActivityComponent>) activity;
        if (holder.getComponent() == null) {
            holder.setComponent(createActivityComponent(activity));
        }
        return holder.getComponent();
    }

    @SuppressWarnings("unchecked")
    private static FragmentComponent getOrCreateFragmentComponent(final Fragment fragment) {
        HasComponent<FragmentComponent> holder = (HasComponent<FragmentComponent>) fragment;
        if (holder.getComponent() == null) {
            holder.setComponent(createFragmentComponent(fragment));
        }
        return holder.getComponent();

    }


    private static ActivityComponent createActivityComponent(final Activity activity) {
        return DaggerActivityComponent
                .builder()
                .activityModule(new ActivityModule())
                .build();
    }


    private static FragmentComponent createFragmentComponent(final Fragment fragment) {
        return DaggerFragmentComponent
                .builder()
                .fragmentModule(new FragmentModule())
                .build();
    }

    private static AndroidApplication getApplication(Context context) {
        return (AndroidApplication) context.getApplicationContext();
    }


}
