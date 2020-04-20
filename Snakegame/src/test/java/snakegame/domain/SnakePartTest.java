package snakegame.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import snakegame.ui.GameBoardViewController;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnakePartTest {


    GameService gs;
    AnchorPane pane;
    GameBoardViewController controller;


    @Before
    public void setUp() {

        pane = new AnchorPane();
        gs = new GameService(pane, controller);
    }

    @Test
    public void switchingHeadDirectionSwitchAlsoAllPartsDirection() throws SQLException {

        gs.startGame();
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
    public void goingUpMakesYSmaller() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goUp();
        part.goUp();
        part.goUp();

        assertEquals(194, part.newY);
    }


    @Test
    public void goingDownMakesYBigger() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goDown();
        part.goDown();
        part.goDown();

        assertEquals(206, part.newY);
    }

    @Test
    public void goingLeftMakesXSmaller() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goLeft();
        part.goLeft();
        part.goLeft();

        assertEquals(194, part.newX);
    }

    @Test
    public void goingRightMakesXBigger() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        SnakePart part = gs.area.head.parts.get(1);

        part.goRight();
        part.goRight();
        part.goRight();

        assertEquals(206, part.newX);
    }

    @Test
    public void hittingRightWallWithoutEatingFirstFoodPutsSnakeToLeftWall() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(200);
        gs.area.head.head.setNewXposition(gs.area.getAreaWidth() - 15);
        gs.area.head.parts.get(0).goRight();

        assertEquals(13, gs.area.head.parts.get(0).newX);
    }

    @Test
    public void hittingLeftWallWithoutEatingFirstFoodPutsSnakeToRightWall() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(200);
        gs.area.head.head.setNewXposition(15);
        gs.area.head.parts.get(0).goLeft();

        assertEquals(gs.area.getAreaWidth() - 10, gs.area.head.parts.get(0).newX);
    }

    @Test
    public void hittingUpWallWithoutEatingFirstFoodPutsSnakeToDownWall() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(15);
        gs.area.head.head.setNewXposition(200);
        gs.area.head.parts.get(0).goUp();

        assertEquals(gs.area.getAreaLength() - 20, gs.area.head.parts.get(0).newY);
    }

    @Test
    public void hittingDownWallWithoutEatingFirstFoodPutsSnakeToUpWall() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setNewYposition(gs.area.getAreaLength() - 30);
        gs.area.head.head.setNewXposition(200);
        gs.area.head.parts.get(0).goDown();

        assertEquals(13, gs.area.head.parts.get(0).newY);
    }
}
