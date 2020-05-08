package snakegame.domain;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
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
    public void snakePartGetRightColorByTheme2() {

        gs.area.theme = "2";
        SnakeHead head = new SnakeHead(20, gs.area);
        gs.area.addNewSnake(head);

        Paint partStroke = gs.area.parts.get(gs.area.parts.size() - 1).getStroke();
        Paint strokeShouldBe = Color.ORANGERED;

        assertEquals(strokeShouldBe, partStroke);
    }

    @Test
    public void snakePartGetRightColorByTheme3() {

        gs.area.theme = "3";
        SnakeHead head = new SnakeHead(20, gs.area);
        gs.area.addNewSnake(head);

        Paint partFill = gs.area.parts.get(gs.area.parts.size() - 1).getStroke();
        Paint fillShouldBe = Color.DARKOLIVEGREEN;

        assertEquals(fillShouldBe, partFill);

        Paint partStroke = gs.area.parts.get(gs.area.parts.size() - 1).getStroke();
        Paint strokeShouldBe = Color.DARKOLIVEGREEN;

        assertEquals(strokeShouldBe, partStroke);
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

        gs.area.update();

        assertFalse(gs.area.hitWall());
    }

    @Test
    public void hittingTheWallWithBordersWillReturnTrue() {

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

    @Test
    public void hittingTheLeftWallWithBordersWillEndTheGame() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(300);

        gs.area.withBorders = true;

        gs.area.head.switchDirection("LEFT");

        while (true) {
            gs.area.update();

            if (gs.area.hitWall()) {
                break;
            }
        }
        assertTrue(gs.area.gameOver);
    }

    @Test
    public void hittingTheRightWallWithBordersWillEndTheGame() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(300);
        gs.area.withBorders = true;

        gs.area.head.switchDirection("RIGHT");

        while (true) {
            gs.area.update();

            if (gs.area.hitWall()) {
                break;
            }
        }
        assertTrue(gs.area.gameOver);
    }

    @Test
    public void hittingTheDownWallWithBordersWillEndTheGame() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(300);
        gs.area.withBorders = true;

        gs.area.head.switchDirection("DOWN");

        while (true) {
            gs.area.update();

            if (gs.area.hitWall()) {
                break;
            }
        }
        assertTrue(gs.area.gameOver);
    }

    @Test
    public void hittingTheUpWallWithBordersWillEndTheGame() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(300);

        gs.area.withBorders = true;

        gs.area.head.switchDirection("UP");

        while (true) {
            gs.area.update();

            if (gs.area.hitWall()) {
                break;
            }
        }
        assertTrue(gs.area.gameOver);
    }

    @Test
    public void hittingItselfWillEndTheGame() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(300);
        gs.area.withBorders = true;

        while (true) {

            gs.area.update();
            gs.area.head.head.setXposition(200);
            gs.area.head.head.setYposition(200);
            gs.area.head.parts.get(5).setXposition(200);
            gs.area.head.parts.get(5).setYposition(200);
            if (gs.area.hitItself()) {
                break;
            }
        }
        assertTrue(gs.area.gameOver);
    }

    @Test
    public void hittingWallWithoutBordersHitWallMethodReturnFalse() {

        gs.area.withBorders = false;
        gs.area.update();

        gs.area.head.head.setXposition(gs.area.getAreaWidth());
        gs.area.head.head.setYposition(200);

        assertFalse(gs.area.hitWall());

        gs.area.head.head.setXposition(0);
        gs.area.head.head.setYposition(200);

        assertFalse(gs.area.hitWall());

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(0);

        assertFalse(gs.area.hitWall());

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(gs.area.getAreaLength());

        assertFalse(gs.area.hitWall());
    }

    @Test
    public void hittingWallWithoutBoardersWillNotEndTheGame() {

        gs.withBorders = false;
        gs.area.update();
        gs.area.head.head.setXposition(gs.area.getAreaWidth());
        gs.area.head.head.setYposition(0);

        assertFalse(gs.area.gameOver);
    }

    @Test
    public void hittingItselfWillReturnTrueIfHit() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(200);
        gs.area.head.parts.get(5).setXposition(200);
        gs.area.head.parts.get(5).setYposition(200);

        boolean t = gs.area.hitItself();
        assertTrue(t);
    }


    @Test
    public void hittingItselfWillReturnFalseIfXPositionIsSame() {

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(100);
        gs.area.head.parts.get(5).setXposition(200);
        gs.area.head.parts.get(5).setYposition(300);

        boolean result = gs.area.hitItself();
        assertFalse(result);

    }

    @Test
    public void hittingItselfWillReturnFalseIfYPositionIsSame() {

        gs.area.head.head.setXposition(100);
        gs.area.head.head.setYposition(250);
        gs.area.head.parts.get(5).setXposition(200);
        gs.area.head.parts.get(5).setYposition(250);

        boolean result = gs.area.hitItself();
        assertFalse(result);
    }

    @Test
    public void gameOverReturnTrueWhenOver() {

        gs.area.gameOver = true;
        gs.area.update();

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

