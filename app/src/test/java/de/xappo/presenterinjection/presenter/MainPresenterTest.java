package de.xappo.presenterinjection.presenter;

import org.junit.Before;
import org.junit.Test;

import de.xappo.presenterinjection.interactor.MainInteractor;
import de.xappo.presenterinjection.model.Person;
import de.xappo.presenterinjection.view.MainView;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by knoppik on 29.10.16.
 */
public class MainPresenterTest {

    private MainPresenter mainPresenter;
    private MainInteractor mainInteractorMock;
    private MainView mainViewMock;

    @Before
    public void setUp() throws Exception {
        mainInteractorMock = mock(MainInteractor.class);
        mainPresenter = new MainPresenter(mainInteractorMock);
        mainViewMock = mock(MainView.class);
        mainPresenter.attachView(mainViewMock);
    }

    @Test
    public void testOnClick_updatePerson() throws Exception {
        String name = "Heinrich";
        Person personMock = new Person(name);

        when(mainInteractorMock.createPerson(name)).thenReturn(personMock);

        mainPresenter.onClick(name);
        verify(mainViewMock).updatePerson(personMock);
    }

}