package snakegame.domain;


import org.junit.Before;
import org.junit.Test;
import javafx.scene.layout.AnchorPane;
import snakegame.ui.GameBoardViewController;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SnakeHeadTest {

    GameService gs;
    AnchorPane pane;
    GameBoardViewController controller;

    @Before
    public void setUp() {
        pane = new AnchorPane();
        controller = new GameBoardViewController();
        controller.borders = true;
        controller.theme = "1";
        gs = new GameService(pane, controller);
        gs.withBorders = true;
        gs.addGameArea();
        gs.move();
        gs.enterPressed();
    }

    @Test
    public void atTheBeginningSnakeHeadGetRightNumberOfSnakeparts() {

        assertEquals(50, gs.area.head.parts.size());
    }

    @Test
    public void settingHeadsDirectionItGetsRightDirection() {


        gs.area.head.setDirection("UP");
        assertEquals("UP", gs.area.head.direction);

        gs.area.head.setDirection("LEFT");
        assertEquals("LEFT", gs.area.head.direction);

        gs.area.head.setDirection("RIGHT");
        assertEquals("RIGHT", gs.area.head.direction);

        gs.area.head.setDirection("DOWN");
        assertEquals("DOWN", gs.area.head.direction);
    }
}
