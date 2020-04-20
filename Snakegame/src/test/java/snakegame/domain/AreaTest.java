package snakegame.domain;

import javafx.scene.paint.Paint;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import snakegame.ui.GameBoardViewController;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AreaTest {
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

    }

    @Test
    public void areaGetRightNumberOfSnakeparts() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertEquals(50, gs.area.parts.size());
    }

    @Test
    public void areaHaveRightLength() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertEquals(gs.area.getAreaLength(), 400);

    }

    @Test
    public void areaHaveRightWidth() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertEquals(gs.area.getAreaWidth(), 600);
    }

    @Test
    public void notHittingTheWallWillContinueTheGame() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setXposition(150);
        gs.area.head.head.setYposition(200);

        assertFalse(gs.area.hitWall());
    }

    @Test
    public void hittingTheWallWillReturnTrue() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setXposition(gs.area.getAreaWidth()-15);
        gs.area.head.head.setYposition(100);
        assertTrue(gs.area.hitWall());

        gs.area.head.head.setXposition(15);
        gs.area.head.head.setYposition(100);
        assertTrue(gs.area.hitWall());

        gs.area.head.head.setXposition(100);
        gs.area.head.head.setYposition(gs.area.getAreaWidth()-15);
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
    public void hittingTheWallWithoutEatingAnythingWillNotEndTheGame() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.update();

        gs.area.head.head.setXposition(gs.area.getAreaWidth());
        gs.area.head.head.setYposition(0);

        assertFalse(gs.area.gameOver);
    }
    @Test
    public void hittingItselfWillReturnTrue() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.head.setXposition(200);
        gs.area.head.head.setYposition(200);
        gs.area.head.parts.get(5).setXposition(200);
        gs.area.head.parts.get(5).setYposition(200);

        boolean t = gs.area.hitItself();
        assertTrue(t);
    }
    /*
    @Test
    public void hittingItselfWillEndTheGame() throws SQLException {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);

        area.addNewSnake(head);
        area.firstEaten=true;
          area.update();

        area.head.head.setXposition(200);
        area.head.head.setYposition(200);
        area.head.parts.get(5).setXposition(200);
        area.head.parts.get(5).setYposition(200);

        area.update();
        System.out.println(area.hitItself());

        assertTrue(area.gameOver());
    } */
    @Test
    public void gameOverReturnTrueWhenOver(){

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.gameOver=true;
        assertTrue(gs.area.gameOver());
    }

}
