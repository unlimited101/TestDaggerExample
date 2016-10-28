package de.xappo.presenterinjection;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;


public class MainActivity extends BaseActivity implements MainView {

    private ActivityComponent activityComponent;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.initializeInjector();

        final EditText editText = (EditText) findViewById(R.id.edittext);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                mainPresenter.onClick(editText.getText().toString());
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    @Override
    public void updatePerson(final Person person) {
        TextView textView = (TextView) findViewById(R.id.textview);
        textView.setText("Hello " + person.getName());
    }

    private void initializeInjector() {
        this.activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }

    @Override
    protected void onActivityCreated() {
        mainPresenter.attachView(this);
    }
}
