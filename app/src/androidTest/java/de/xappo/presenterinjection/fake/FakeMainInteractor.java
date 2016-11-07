package de.xappo.presenterinjection.fake;

import de.xappo.presenterinjection.interactor.MainInteractor;
import de.xappo.presenterinjection.model.Person;

/**
 * Created by knoppik on 03.11.16.
 */

public class FakeMainInteractor extends MainInteractor {
    public Person createPerson(final String name) {
        return new Person("Fake Person");
    }
}
