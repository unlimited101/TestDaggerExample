package de.xappo.presenterinjection.presenter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import de.xappo.presenterinjection.DiceThrower;
import de.xappo.presenterinjection.interactor.MainInteractor;
import de.xappo.presenterinjection.model.Person;
import de.xappo.presenterinjection.view.MainView;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyList;
import static org.mockito.Matchers.anyString;
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
    private DiceThrower diceThrowerMock;

    @Before
    public void setUp() throws Exception {
        mainInteractorMock = mock(MainInteractor.class);
        mainPresenter = new MainPresenter(mainInteractorMock);
        mainViewMock = mock(MainView.class);
        mainPresenter.attachView(mainViewMock);
        diceThrowerMock = mock(DiceThrower.class);
        mainPresenter.diceThrower = diceThrowerMock;
    }

    @Test
    public void testOnClick_updatePerson() throws Exception {
        String name = "Heinrich";
        Person personMock = new Person(name);

        when(mainInteractorMock.createPerson(name)).thenReturn(personMock);

        mainPresenter.onClick(name);
        verify(mainViewMock).updatePerson(personMock);
    }

    @Test
    public void testOnClick_throwDices() throws Exception {
        mainPresenter.onClick(anyString());
        verify(mainViewMock).updateDiceResult(anyInt(), anyInt());
    }

    @Test
    public void testOnClick_throwDicesCorrectResult() throws Exception {
        DiceThrower diceThrower = new DiceThrower();
        final int numberOfThrows = 3;
        final int numberOfPossibleNumbers = 20;
        diceThrower.throwDices(numberOfThrows, numberOfPossibleNumbers);

        when(diceThrowerMock.getResult()).thenReturn(diceThrower.getResult());
        when(diceThrowerMock.getDices()).thenReturn(new ArrayList<Integer>() {{
            add(3);
            add(2);
            add(1);
        }});

        mainPresenter.onClick("mame");
        verify(mainViewMock).updateDiceResult(diceThrower.getDices().size(), diceThrower.getResult());
    }
}