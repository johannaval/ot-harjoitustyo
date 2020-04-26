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
    }

    @Test
    public void switchingHeadDirectionSwitchAlsoAllPartsDirection() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

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

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goUp();
        part.goUp();
        part.goUp();

        assertEquals(194, part.newY);
    }


    @Test
    public void goingDownMakesYBigger() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goDown();
        part.goDown();
        part.goDown();

        assertEquals(206, part.newY);
    }

    @Test
    public void goingLeftMakesXSmaller() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goLeft();
        part.goLeft();
        part.goLeft();

        assertEquals(194, part.newX);
    }

    @Test
    public void goingRightMakesXBigger() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goRight();
        part.goRight();
        part.goRight();

        assertEquals(206, part.newX);
    }

    @Test
    public void hittingRightWallWithoutBordersPutsSnakeToLeftWall() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(200);
        gs.area.head.head.setNewXposition(gs.area.getAreaWidth());
        gs.area.head.parts.get(0).goRight();

        assertEquals(0, gs.area.head.parts.get(0).newX);
    }

    @Test
    public void hittingLeftWallWithoutBordersPutsSnakeToRightWall() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(200);
        gs.area.head.head.setNewXposition(0);
        gs.area.head.parts.get(0).goLeft();

        assertEquals(600, gs.area.head.parts.get(0).newX);
    }

    @Test
    public void hittingUpWallWithoutBordersPutsSnakeToDownWall() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(0);
        gs.area.head.head.setNewXposition(200);
        gs.area.head.parts.get(0).goUp();

        assertEquals(400, gs.area.head.parts.get(0).newY);
    }

    @Test
    public void hittingDownWallWithoutBordersPutsSnakeToUpWall() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(gs.area.getAreaLength());
        gs.area.head.head.setNewXposition(200);
        gs.area.head.parts.get(0).goDown();

        assertEquals(0, gs.area.head.parts.get(0).newY);
    }
}
