package de.xappo.presenterinjection;

import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by knoppik on 27.10.16.
 */

@PerActivity
public class MainPresenter implements MvpPresenter<MainView> {
    private MainView mainView;

    @Inject
    public MainPresenter() {

    }

    public void onClick(String name) {
        Person person = new Person(name);
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