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
    public void switchingHeadDirectionSwitchAlsoAllPartsDirection() throws SQLException {

        head.switchDirection("UP");
        assertEquals("UP", head.parts.get(1).getDirection());

        head.switchDirection("DOWN");
        assertEquals("DOWN", head.parts.get(1).getDirection());

        head.switchDirection("RIGHT");
        assertEquals("RIGHT", head.parts.get(1).getDirection());

        head.switchDirection("LEFT");
        assertEquals("LEFT", head.parts.get(1).getDirection());
    }

    @Test
    public void goingUpMakesYSmaller() throws SQLException {

        SnakePart part = head.parts.get(1);

        part.goUp();
        part.goUp();
        part.goUp();

        assertEquals(194, part.newY);
    }


    @Test
    public void goingDownMakesYBigger() throws SQLException {

        SnakePart part = head.parts.get(1);

        part.goDown();
        part.goDown();
        part.goDown();

        assertEquals(206, part.newY);
    }

    @Test
    public void goingLeftMakesXSmaller() throws SQLException {

        SnakePart part = head.parts.get(1);

        part.goLeft();
        part.goLeft();
        part.goLeft();

        assertEquals(294, part.newX);
    }

    @Test
    public void goingRightMakesXBigger() throws SQLException {

        SnakePart part = head.parts.get(1);

        part.goRight();
        part.goRight();
        part.goRight();

        assertEquals(306, part.newX);
    }

    @Test
    public void hittingRightWallWithoutEatingFirstFoodPutsSnakeToLeftWall() throws SQLException {

        head.head.setNewYposition(200);
        head.head.setNewXposition(area.getAreaWidth() - 15);
        head.parts.get(0).goRight();

        assertEquals(13, head.parts.get(0).newX);
    }

    @Test
    public void hittingLeftWallWithoutEatingFirstFoodPutsSnakeToRightWall() throws SQLException {

        head.head.setNewYposition(200);
        head.head.setNewXposition(15);
        head.parts.get(0).goLeft();

        assertEquals(area.getAreaWidth() - 28, head.parts.get(0).newX);
    }

    @Test
    public void hittingUpWallWithoutEatingFirstFoodPutsSnakeToDownWall() throws SQLException {

        head.head.setNewYposition(15);
        head.head.setNewXposition(200);
        head.parts.get(0).goUp();

        assertEquals(area.getAreaLength() - 20, head.parts.get(0).newY);
    }

    @Test
    public void hittingDownWallWithoutEatingFirstFoodPutsSnakeToUpWall() throws SQLException {

        head.head.setNewYposition(area.getAreaLength() - 30);
        head.head.setNewXposition(200);
        head.parts.get(0).goDown();

        assertEquals(13, head.parts.get(0).newY);
    }
}
