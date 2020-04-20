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
    public void isThereAccountWithThisNameReturnTrueIfThereIs() throws SQLException {

        service.createUser("kyna", "kotelo");

        boolean result = service.isThereAccountWithThisName("kyna");

        assertTrue(result);
    }

    @Test
    public void isThereAccountWithThisNameReturnFalseIfThereIsNot() throws SQLException {

        boolean result = service.isThereAccountWithThisName("liitovarjo");

        assertFalse(result);
    }

}