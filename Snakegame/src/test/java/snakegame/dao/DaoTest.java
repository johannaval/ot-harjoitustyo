package snakegame.dao;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import snakegame.domain.Player;

public class DaoTest {

    PlayerSQL pd;

    public DaoTest() {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream("config.properties"));
        } catch (IOException e) {
            System.out.println("Konfiguroinnissa virhe!");
        }

        String urlForDaoUnitTests = properties.getProperty("urlForDaoUnitTests");

        this.pd = new PlayerSQL(urlForDaoUnitTests);
    }

    @BeforeEach
    public void setUp() {
        pd.clear();

    }

    @Test
    public void creatingNewPersonWorks() {

        setUp();

        Player player = new Player("testName3", "test", 0);
        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("testName3").getUsername(), "testName3");
    }

    @Test
    public void updatingHighscoreWorks() {

        setUp();

        Player player = new Player("testaaja", "test", 0);
        pd.createTable();
        pd.create(player);
        player.putHighscore(100);

        pd.update(player);

        assertEquals(100, pd.findUser("testaaja").getHighscore());
    }

    @Test
    public void tableGetPlayer() {

        setUp();

        Player player = new Player("testName2", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("testName2"), player);

    }

    @Test
    public void tableDoesFindNonExistingPlayer() {

        setUp();

        pd.createTable();

        assertEquals(null, pd.findUser("mikko"));

    }

    @Test
    public void isThereAccountWithThisNameReturnTrueIfThereIs() {

        setUp();

        Player p = new Player("benji", "hyppääjä", 0);
        pd.createTable();
        pd.create(p);

        assertTrue(pd.isThereAccountWithThisName("benji"));
    }

    @Test
    public void isThereAccountWithThisNameReturnFalseIfThereIsNot() {

        setUp();

        pd.createTable();

        assertFalse(pd.isThereAccountWithThisName("sammakko"));
    }

    @Test
    public void passwordIsCorrect() {

        setUp();

        Player player = new Player("testName5", "test", 0);

        pd.createTable();
        pd.create(player);

        assertEquals(pd.findUser("testName5").password, "test");

    }

    @Test
    public void logInReturnTrueIfPlayerIsInTable() {

        setUp();

        Player playerNew = new Player("testName1", "test", 0);

        pd.createTable();
        pd.create(playerNew);

        Player testPerson = pd.isLogInOK("testName1", "test");

        assertTrue(testPerson.equals(playerNew));

    }

    @Test
    public void logInreturnNullIfPlayerIsNotInTable() {

        setUp();

        pd.createTable();
        assertNull(pd.isLogInOK("usernotintable", "test"));

    }

    @Test
    public void topListReturnRightSizeListIfAllPlayerGotMoreThan0Points() {

        setUp();

        pd.createTable();

        ObservableList<Player> emptyList = FXCollections.observableArrayList();

        Player tester1 = new Player("testName1", "test", 100);
        pd.create(tester1);

        Player tester2 = new Player("testName2", "test", 300);
        pd.create(tester2);

        Player tester3 = new Player("testName3", "test", 1000);
        pd.create(tester3);


        ObservableList<Player> listWithScores = pd.topList(emptyList);


        assertEquals(3, listWithScores.size());
    }

    @Test
    public void topListReturnRightSizeListIfSomeoneGot0Points() {

        setUp();

        pd.createTable();

        ObservableList<Player> emptyList = FXCollections.observableArrayList();

        Player tester1 = new Player("testName1", "test", 0);
        pd.create(tester1);

        Player tester2 = new Player("testName2", "test", 300);
        pd.create(tester2);

        Player tester3 = new Player("testName3", "test", 1000);
        pd.create(tester3);

        ObservableList<Player> listWithScores = pd.topList(emptyList);

        assertEquals(2, listWithScores.size());
    }
}



