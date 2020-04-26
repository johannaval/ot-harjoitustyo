package snakegame.domain;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.layout.AnchorPane;
import snakegame.ui.GameBoardViewController;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AreaTest {

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
        gs.addGameArea();
        gs.move();
        gs.enterPressed();

    }

    @Test
    public void areaGetRightNumberOfSnakeparts() {

        assertEquals(50, gs.area.parts.size());
    }

    @Test
    public void areaHaveRightLength() {

        assertEquals(gs.area.getAreaLength(), 400);

    }

    @Test
    public void areaHaveRightWidth() {

        assertEquals(gs.area.getAreaWidth(), 600);
    }

    @Test
    public void pointsTextGetRightColorByTheme() {

        gs.theme = "1";
        assertEquals(gs.text.getStroke(), Color.WHITE);

        gs.theme = "2";
        assertEquals(gs.text.getStroke(), Color.WHITE);

        gs.theme = "1";
        assertEquals(gs.text.getStroke(), Color.WHITE);
    }

    @Test
    public void notHittingTheWallWillContinueTheGame() {

        gs.area.head.head.setXposition(150);
        gs.area.head.head.setYposition(200);

        assertFalse(gs.area.hitWall());
    }

    @Test
    public void hittingTheWallWillReturnTrue() {

        gs.withBorders = true;

        gs.area.head.head.setXposition(gs.area.getAreaWidth() - 15);
        gs.area.head.head.setYposition(100);
        assertTrue(gs.area.hitWall());

        gs.area.head.head.setXposition(15);
        gs.area.head.head.setYposition(100);
        assertTrue(gs.area.hitWall());

        gs.area.head.head.setXposition(100);
        gs.area.head.head.setYposition(gs.area.getAreaWidth() - 15);
        assertTrue(gs.area.hitWall());

        gs.area.head.head.setXposition(100);
        gs.area.head.head.setYposition(15);
        assertTrue(gs.area.hitWall());
    }

    /* @Test
     public void hittingTheWallWillEndTheGameIfPointsAreNotZero() {

         AnchorPane pane = new AnchorPane();
         GameService gs = new GameService(pane, controller);
         Area area = new Area(300, 600, pane);

         SnakeHead head = new SnakeHead(20, area);
         area.addNewSnake(head);

         area.update();

         area.firstEaten=true;

         area.head.head.setXposition(area.getAreaWidth());
         area.head.head.setYposition(0);

         System.out.println(area.head.head.getXposition());
         System.out.println(area.head.head.getYposition());


         assertTrue(area.gameOver);

     } */


    @Test
    public void hittingEdgeWithoutBoardersWillNotEndTheGame() {

        gs.withBorders = false;

        gs.area.update();

        gs.area.head.head.setXposition(gs.area.getAreaWidth());
        gs.area.head.head.setYposition(0);

        assertFalse(gs.area.gameOver);
    }

    @Test
    public void hittingItselfWillReturnTrue() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(200);
        gs.area.head.parts.get(5).setXposition(200);
        gs.area.head.parts.get(5).setYposition(200);

        boolean t = gs.area.hitItself();
        assertTrue(t);
    }

/*
    @Test
    public void hittingItselfWillEndTheGame() {

        SnakeHead head = new SnakeHead(20, gs.area);

        gs.area.addNewSnake(head);
        gs.area.enterPressed=true;
        //  gs.area.update();

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(200);
        gs.area.head.parts.get(5).setXposition(200);
        gs.area.head.parts.get(5).setYposition(200);

        System.out.println(gs.area.head.head.getXposition());
        gs.area.head.parts.get(5).getXposition();

       // gs.area.update();
        System.out.println(gs.area.hitItself());

        assertTrue(gs.area.gameOver());
    } */
    @Test
    public void gameOverReturnTrueWhenOver() {

        gs.area.gameOver = true;
        assertTrue(gs.area.gameOver());
    }

    @Test
    public void goUpPutSnakeGoUp() {

        gs.goUp();

        assertEquals(gs.area.head.direction, "UP");

    }

    @Test
    public void goDownPutSnakeGoDown() {

        gs.goDown();

        assertEquals(gs.area.head.direction, "DOWN");

    }

    @Test
    public void goRightPutSnakeGoRight() {

        gs.goRigh();

        assertEquals(gs.area.head.direction, "RIGHT");

    }

    @Test
    public void goLeftPutSnakeGoLeft() {

        gs.goLeft();

        assertEquals(gs.area.head.direction, "LEFT");

    }
}

