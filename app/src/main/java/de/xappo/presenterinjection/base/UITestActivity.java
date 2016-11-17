package de.xappo.presenterinjection.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.di.components.ActivityComponent;
import de.xappo.presenterinjection.di.components.HasComponent;

/**
 * Small Activity for Activity-UnitTests in order to receive an Activity object run with an
 * IntentActivityRule
 */
public class UITestActivity extends BaseActivity implements
        HasComponent<ActivityComponent> {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.setId(R.id.fragmentContainer);
        setContentView(frameLayout);
    }
}
