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
        this.pane = new AnchorPane();
        this.gs = new GameService(pane, controller);
    }

    @Test
    public void atTheBeginningDirectionIsRight() {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertEquals("RIGHT", gs.area.head.head.getDirection());
    }

    @Test
    public void atTheBeginningPointsAreZero() {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertEquals(0, gs.area.getPoints());
    }

    @Test
    public void atTheBeginningBooleanGameOverIsFalse() throws IOException, SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertFalse(gs.area.gameOver);
    }
}
