package de.xappo.presenterinjection.interactor;

import android.util.Log;

import de.xappo.presenterinjection.model.Person;

/**
 * Created by knoppik on 28.10.16.
 */
public class MainInteractor {
    private static final String TAG = "MainInteractor";

    public MainInteractor() {
        Log.i(TAG, "constructor");
    }

    public Person createPerson(final String name) {
        return new Person(name);
    }
}
