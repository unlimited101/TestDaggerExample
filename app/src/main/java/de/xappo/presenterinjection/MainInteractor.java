package de.xappo.presenterinjection;

/**
 * Created by knoppik on 28.10.16.
 */
public class MainInteractor {
    public Person createPerson(final String name) {
        return new Person(name);
    }
}
