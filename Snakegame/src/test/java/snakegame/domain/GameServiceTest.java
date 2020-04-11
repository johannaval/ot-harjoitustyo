package snakegame.domain;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.robot.Robot;
import org.junit.Test;

import static java.lang.System.currentTimeMillis;
import static javafx.scene.input.KeyCode.*;
import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import snakegame.ui.GameBoardViewController;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameServiceTest {

    Area area;
    GameService gs;
    SnakePart part;
    SnakeHead head;
    AnchorPane pane;
    GameBoardViewController controller;

    public GameServiceTest() {

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

   /* @Test
    public void pressingKeyCodeUpSetDirectionUp() {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        gs.move();

        Robot rob = new Robot();
        pane.setFocusTraversable(true);
        pane.requestFocus();
        rob.keyPress(UP);

        KeyEvent key = new KeyEvent(pane, KeyEvent.KEY_PRESSED, currentTimeMillis(), 0,  UP);
        instance.getKeyListeners()[0].keyPressed(key);


        assertEquals(50, area.getPoints()); */

    }

