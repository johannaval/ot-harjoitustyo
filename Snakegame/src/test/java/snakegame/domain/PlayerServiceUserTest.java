package snakegame.domain;

import org.junit.Before;
import org.junit.Test;
import snakegame.ui.LogInViewController;
import static org.junit.Assert.*;
import java.sql.SQLException;


public class PlayerServiceUserTest {

    FakePlayerSQL playerSQL;
    PlayerService service;
    LogInViewController controller;

    @Before
    public void setUp() throws SQLException {
        playerSQL = new FakePlayerSQL();
        controller = new LogInViewController();
        service = new PlayerService(playerSQL, controller);
    }

    @Test
    public void updatingHighscoreWorks() throws SQLException {

        boolean creatingResult = service.createUser("lasku", "varjo");
        assertTrue(creatingResult);

        boolean loginResult = service.login("lasku", "varjo");
        assertTrue(loginResult);

        service.getLoggedUser().putHighscore(100);

        assertEquals(100, service.getLoggedUser().getHighscore());
    }


    @Test
    public void isThereAccountWithThisNameReturnTrueIfThereIs() throws SQLException {

        service.createUser("Kyna", "Kotelo");

        boolean result = service.isThereAccountWithThisName("Kyna");

        assertTrue(result);
    }

    @Test
    public void isThereAccountWithThisNameReturnFalseIfThereIsNot() throws SQLException {

        boolean result = service.isThereAccountWithThisName("Liitovarjo");

        assertFalse(result);
    }

    @Test
    public void existingUserCanLogIn() throws SQLException {

        boolean result = service.login("lento", "kone");
        assertTrue(result);
        Player loggedIn = service.getLoggedUser();

        assertEquals("lento", loggedIn.getUsername());
    }

    @Test
    public void logOutAfterLoggingIn() throws SQLException {

        boolean result = service.login("lento", "kone");
        assertTrue(result);

        service.logout();

        assertEquals(null, service.getLoggedUser());
    }

    @Test
    public void playerEqualOtherWithSameData() {

        Player first = new Player("heli", "kopteri", 0);
        Player second = new Player("heli", "kopteri", 0);

        boolean result = first.equals(second);

        assertTrue(result);
    }

    @Test
    public void playerDoesNotEqualOtherWithDifferentName() {

        Player first = new Player("ilma", "pallo", 0);
        Player second = new Player("lento", "pallo", 0);

        boolean result = first.equals(second);

        assertFalse(result);
    }

    @Test
    public void updatingHighshcoreWorks() throws SQLException {

        boolean creatingResult = service.createUser("mikko", "mallikas");
        assertTrue(creatingResult);

        boolean loginResult = service.login("mikko", "mallikas");
        assertTrue(loginResult);

        service.setHighscore(500);

        assertEquals(500, service.getLoggedUser().highscore);

    }

    @Test
    public void updatingHighshcoreDontUpdateIfScoreIsLessThanHighscore() throws SQLException {

        boolean creatingResult = service.createUser("mikko", "mallikas");
        assertTrue(creatingResult);

        boolean loginResult = service.login("mikko", "mallikas");
        assertTrue(loginResult);

        service.setHighscore(500);
        service.setHighscore(300);

        assertEquals(500, service.getLoggedUser().highscore);

    }

    @Test
    public void PlayerTypeDoesNotEqualObjectType() {

        Player first = new Player("liito", "orava", 0);
        Object second = new Object();
        boolean result = first.equals(second);

        assertFalse(result);
    }

    @Test
    public void nonExistingUserCanNotLogIn() throws SQLException {

        boolean result = service.login("tester", "test");
        assertFalse(result);

        assertEquals(null, service.getLoggedUser());

    }

    @Test
    public void creationFailsIfThereIsAlreadyUserWithThisName() throws Exception {

        boolean result = service.createUser("lento", "simulaattori");

        assertFalse(result);
    }


    @Test
    public void createdUserCanLogIn() throws Exception {

        boolean result = service.createUser("ilma", "pallo");
        assertTrue(result);

        boolean resultLogin = service.login("ilma", "pallo");
        assertTrue(resultLogin);

        assertEquals("ilma", service.getLoggedUser().getUsername());
    }
}


