package snakegame.dao;


import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import snakegame.domain.Player;

public class DaoTest {

    PlayerSQL pd;

    public DaoTest() throws SQLException {

        this.pd = new PlayerSQL("jdbc:sqlite:thisIsForUnittestss.db");
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() throws SQLException {
        pd.clear();
    }

    @Test
    public void testParse() {
    }

    @Test
    public void creatingNewPersonWorks() throws SQLException {

        tearDown();

        Player player = new Player("TestName3", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("TestName3").getUsername(), "TestName3");
    }

    @Test
    public void updatingHighscoreWorks() throws SQLException {

        tearDown();

        Player player = new Player("Testaaja", "test", 0);
        pd.createTable();
        pd.create(player);
        player.putHighscore(100);

        pd.update(player);

        assertEquals(100, pd.findUser("Testaaja").getHighscore());
    }

    @Test
    public void tableGetPlayer() throws SQLException {

        tearDown();

        Player player = new Player("TestName2", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("TestName2"), player);

    }

    @Test
    public void tableDoesFindNonExistingPlayer() throws SQLException {

        tearDown();

        pd.createTable();

        assertEquals(null, pd.findUser("Mikko"));

    }

    @Test
    public void isThereAccountWithThisNameReturnTrueIfThereIs() throws SQLException {

        tearDown();

        Player p = new Player("benji", "hyppääjä", 0);
        pd.createTable();
        pd.create(p);
        assertTrue(pd.isThereAccountWithThisName("benji"));
    }

    @Test
    public void isThereAccountWithThisNameReturnFalseIfThereIsNot() throws SQLException {
        tearDown();

        pd.createTable();
        assertFalse(pd.isThereAccountWithThisName("sammakko"));
    }

    @Test
    public void passwordIsCorrect() throws SQLException {

        tearDown();

        Player player = new Player("TestName5", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("TestName5").password, "test");

    }

    @Test
    public void logInReturnTrueIfPlayerIsInTable() throws SQLException {

        tearDown();


        Player playerNew = new Player("TestName1", "test", 0);

        pd.createTable();
        pd.create(playerNew);

        Player testPerson = pd.isLogInOK("TestName1", "test");

        assertTrue(testPerson.equals(playerNew));

    }

    @Test
    public void logInreturnNullIfPlayerIsNotInTable() throws SQLException {

        tearDown();

        pd.createTable();
        assertNull(pd.isLogInOK("UserNotInTable", "test"));

    }
}


