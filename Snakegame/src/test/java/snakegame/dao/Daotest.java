package snakegame.dao;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import snakegame.dao.GameDao;

import java.sql.SQLException;

public class Daotest {

    PlayerDao pd;

    public Daotest() {

       this.pd = new PlayerDao("jdbc:sqlite:thisIsForUnittests.db");

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
    public void tearDown() {
    }

    @Test
    public void creatingNewPersonWorks() throws SQLException {

        Player player = new Player("TestName3", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("TestName3").getName(),"TestName3");
    }

    @Test
    public void tableGetPlayer() throws SQLException {

        Player player = new Player("TestName2", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("TestName2"), player);

    }
    @Test
    public void logInReturnTrueIfPlayerIsInTable() throws SQLException {

        Player playerNew = new Player("TestName1", "test", 0);

        pd.createTable();
        pd.create(playerNew);

        Player testPerson = pd.isLogInOK("TestName1","test");

        assertTrue(testPerson.equals(playerNew));

    }
    @Test
    public void logInreturnNullIfPlayerIsNotInTable() throws SQLException {

        pd.createTable();
        Player notInTable = pd.isLogInOK("TestaajaFake","test");

        assertEquals(pd.isLogInOK("notInTable","test"),null);

    }
}