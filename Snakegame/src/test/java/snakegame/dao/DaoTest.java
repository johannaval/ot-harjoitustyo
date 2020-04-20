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

    @AfterEach
    public void tearDown() throws SQLException {
        pd.clear();
    }


    @Test
    public void creatingNewPersonWorks() throws SQLException {

        tearDown();

        Player player = new Player("testName3", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("testName3").getUsername(), "testName3");
    }

    @Test
    public void tableGetPlayer() throws SQLException {

        tearDown();

        Player player = new Player("testName2", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("testName2"), player);

    }

    @Test
    public void tableDoesFindNonExistingPlayer() throws SQLException {

        tearDown();

        pd.createTable();

        assertEquals(null, pd.findUser("mikko"));

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

        Player player = new Player("testName5", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("testName5").password, "test");

    }

    @Test
    public void logInReturnTrueIfPlayerIsInTable() throws SQLException {

        tearDown();


        Player playerNew = new Player("testName1", "test", 0);

        pd.createTable();
        pd.create(playerNew);

        Player testPerson = pd.isLogInOK("testName1", "test");

        assertTrue(testPerson.equals(playerNew));

    }
}


