package snakegame.domain;

import org.junit.Test;
import javafx.scene.layout.AnchorPane;
import snakegame.ui.GameBoardViewController;
import org.junit.Before;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class SnakePartTest {

    GameService gs;
    AnchorPane pane;
    GameBoardViewController controller;

    @Before
    public void setUp() {

        pane = new AnchorPane();
        controller = new GameBoardViewController();
        controller.borders=true;
        controller.theme="1";
        gs = new GameService(pane, controller);
        gs.addGameArea();
        gs.move();
    }

    @Test
    public void switchingHeadDirectionSwitchAlsoAllPartsDirection() {

        gs.area.head.switchDirection("UP");
        assertEquals("UP", gs.area.head.parts.get(1).getDirection());

        gs.area.head.switchDirection("DOWN");
        assertEquals("DOWN", gs.area.head.parts.get(1).getDirection());

        gs.area.head.switchDirection("RIGHT");
        assertEquals("RIGHT", gs.area.head.parts.get(1).getDirection());

        gs.area.head.switchDirection("LEFT");
        assertEquals("LEFT", gs.area.head.parts.get(1).getDirection());
    }

    @Test
    public void goingUpMakesYSmaller() {

        SnakePart part = gs.area.head.parts.get(1);

        part.goUp();
        part.goUp();
        part.goUp();

        assertEquals(194, part.newY);
    }


    @Test
    public void goingDownMakesYBigger() {

        SnakePart part = gs.area.head.parts.get(1);

        part.goDown();
        part.goDown();
        part.goDown();

        assertEquals(206, part.newY);
    }

    @Test
    public void goingLeftMakesXSmaller() {

        SnakePart part = gs.area.head.parts.get(1);

        part.goLeft();
        part.goLeft();
        part.goLeft();

        assertEquals(194, part.newX);
    }

    @Test
    public void goingRightMakesXBigger() {

        SnakePart part = gs.area.head.parts.get(1);

        part.goRight();
        part.goRight();
        part.goRight();

        assertEquals(206, part.newX);
    }

    @Test
    public void hittingRightWallWithoutBordersPutsSnakeToLeftWall() {


        gs.area.head.head.setNewYposition(200);
        gs.area.head.head.setNewXposition(gs.area.getAreaWidth());
        gs.area.head.head.goRight();

        assertEquals(0, gs.area.head.parts.get(0).newX);
    }

    @Test
    public void hittingLeftWallWithoutBordersPutsSnakeToRightWall() {


        gs.area.head.head.setNewYposition(200);
        gs.area.head.head.setNewXposition(0);
        gs.area.head.head.goLeft();

        assertEquals(600, gs.area.head.parts.get(0).newX);
    }

    @Test
    public void hittingUpWallWithoutBordersPutsSnakeToDownWall() {

        gs.area.head.head.setNewYposition(0);
        gs.area.head.head.setNewXposition(200);
        gs.area.head.head.goUp();

        assertEquals(400, gs.area.head.parts.get(0).newY);
    }

    @Test
    public void hittingDownWallWithoutBordersPutsSnakeToUpWall() {

        gs.area.head.head.setNewYposition(gs.area.getAreaLength());
        gs.area.head.head.setNewXposition(200);
        gs.area.head.head.goDown();

        assertEquals(0, gs.area.head.parts.get(0).newY);
    }
}
