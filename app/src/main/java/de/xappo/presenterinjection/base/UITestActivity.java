package de.xappo.presenterinjection.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.utils.HasComponent;

/**
 * Created by knoppik on 11.11.16.
 */
public class UITestActivity extends AppCompatActivity implements
        HasComponent<ActivityComponent> {
    private ActivityComponent mComponent;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public ActivityComponent getComponent() {
        return mComponent;
    }

    @Override
    public void setComponent(ActivityComponent component) {
        mComponent = component;
    }

}
