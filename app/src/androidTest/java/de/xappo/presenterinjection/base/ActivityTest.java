package de.xappo.presenterinjection.base;

import org.junit.Before;

import de.xappo.presenterinjection.di.components.TestActivityComponent;

/**
 * Created by knoppik on 11.11.16.
 */
public class ActivityTest {

    private TestActivityComponent mTestActivityComponent;

    @Before
    public void setUp() {
        initializeInjector();
    }

    protected void initializeInjector() {

    }

}
