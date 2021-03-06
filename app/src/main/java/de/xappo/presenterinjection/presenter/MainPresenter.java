package de.xappo.presenterinjection.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import javax.inject.Inject;

import de.xappo.presenterinjection.di.scopes.PerActivity;
import de.xappo.presenterinjection.di.scopes.PerFragment;
import de.xappo.presenterinjection.interactor.MainInteractor;
import de.xappo.presenterinjection.model.Person;
import de.xappo.presenterinjection.view.MainView;

/**
 * Created by knoppik on 27.10.16.
 */

@PerFragment
public class MainPresenter implements MvpPresenter<MainView> {
    private static final String TAG = "MainPresenter";
    private MainView mainView;
    private MainInteractor mainInteractor;


    @Inject
    public MainPresenter(MainInteractor mainInteractor) {
        Log.i(TAG, "injectDagger constrcutor");

        this.mainInteractor = mainInteractor;
    }

    public void onClick(String name) {
        Log.i(TAG, "injectDagger mainInteractor used: " + mainInteractor.getClass().getName());
        Person person = mainInteractor.createPerson(name);
        mainView.updatePerson(person);
    }

    @Override
    public void attachView(@NonNull final MainView view) {
        mainView = view;
    }

    @Override
    public void detachView() {
        mainView = null;

    }
}
