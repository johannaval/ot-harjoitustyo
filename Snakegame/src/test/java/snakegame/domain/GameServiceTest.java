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
        gs.move();
        gs.enterPressed();
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


        assertFalse(gs.area.gameOver);
    }
}
