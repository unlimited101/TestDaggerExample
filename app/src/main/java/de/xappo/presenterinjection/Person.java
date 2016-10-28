package de.xappo.presenterinjection;

/**
 * Created by knoppik on 27.10.16.
 */

public class Person {
    private String name;

    public Person(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
