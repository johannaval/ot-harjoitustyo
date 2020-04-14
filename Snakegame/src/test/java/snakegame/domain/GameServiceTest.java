package snakegame.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import snakegame.ui.GameBoardViewController;
import java.io.IOException;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameServiceTest {

    Area area;
    GameService gs;
    SnakePart part;
    SnakeHead head;
    AnchorPane pane;
    GameBoardViewController controller;

    @Before
    public void setUp() {
        pane = new AnchorPane();
        gs = new GameService(pane, controller);
        area = new Area(300, 600, pane);
        head = new SnakeHead(20, area);
        area.addNewSnake(head);
    }

    @Test
    public void atTheBeginningDirectionIsRight() {

        gs.startGame();

        assertEquals("RIGHT", area.head.head.getDirection());
    }

    @Test
    public void atTheBeginningPointsAreZero() {

        gs.startGame();

        assertEquals(0, area.getPoints());
    }

    @Test
    public void atTheBeginningBooleanGameOverIsFalse() throws IOException, SQLException {

        gs.startGame();

        assertFalse(area.gameOver);
    }


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

