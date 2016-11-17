package de.xappo.presenterinjection.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import de.xappo.presenterinjection.R;
import de.xappo.presenterinjection.base.BaseFragment;
import de.xappo.presenterinjection.model.Person;
import de.xappo.presenterinjection.presenter.MainPresenter;


public class MainFragment extends BaseFragment implements MainView {

    private static final String TAG = "MainFragment";
    @Inject
    MainPresenter mainPresenter;
    private View view;

    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //((MainActivity)getActivity()).getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    public void onClick(final String s) {
        mainPresenter.onClick(s);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getComponent().inject(this);

        final EditText editText = (EditText) view.findViewById(R.id.edittext);
        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                MainFragment.this.onClick(editText.getText().toString());
            }
        });
        mainPresenter.attachView(this);
    }

    @Override
    public void updatePerson(final Person person) {
        TextView textView = (TextView) view.findViewById(R.id.textview_greeting);
        textView.setText("Hello " + person.getName());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainPresenter.detachView();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
