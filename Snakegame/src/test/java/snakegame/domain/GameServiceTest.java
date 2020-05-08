package snakegame.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.AnchorPane;
import snakegame.ui.GameBoardViewController;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class GameServiceTest {

    GameService gs;
    AnchorPane pane;
    GameBoardViewController controller;

    @Before
    public void setUp() {
        this.pane = new AnchorPane();
        controller = new GameBoardViewController();
        controller.borders = true;
        controller.theme = "1";
        this.gs = new GameService(pane, controller);
        gs.addGameArea();
        gs.withBorders = true;
        gs.move();
    }

    @Test
    public void atTheBeginningDirectionIsRight() {

        assertEquals("RIGHT", gs.area.head.head.getDirection());
    }

    @Test
    public void atTheBeginningPointsAreZero() {

        assertEquals(0, gs.area.getPoints());
    }

    @Test
    public void atTheBeginningBooleanGameOverIsFalse() {

        gs.area.withBorders = false;
        SnakeHead head = new SnakeHead(20, gs.area);
        gs.area.addNewSnake(head);

        assertFalse(gs.gameOver);
        assertFalse(gs.area.gameOver);
    }
}
