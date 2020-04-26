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
        controller.borders=true;
        controller.theme="1";
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
    public void atTheBeginningBooleanGameOverIsFalse()  {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertFalse(gs.area.gameOver);
    }
}
