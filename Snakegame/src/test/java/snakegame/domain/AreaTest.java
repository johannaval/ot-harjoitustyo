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
        area = new Area(300, 600, pane);
        head = new SnakeHead(20, area);
        area.addNewSnake(head);

    }

    @Test
    public void areaGetRightNumberOfSnakeparts() throws SQLException {

        assertEquals(area.parts.size(), 20);
    }

    @Test
    public void areaHaveRightLength() throws SQLException {

        assertEquals(area.getAreaLength(), 300);

    }

    @Test
    public void areaHaveRightWidth() throws SQLException {

        assertEquals(area.getAreaWidth(), 600);
    }

    @Test
    public void notHittingTheWallWillContinueTheGame() throws SQLException {

        area.head.head.setXposition(150);
        area.head.head.setYposition(200);

        assertFalse(area.hitWall());
    }

    @Test
    public void hittingTheWallWillReturnTrue() throws SQLException {

        area.head.head.setXposition(area.getAreaWidth()-15);
        area.head.head.setYposition(100);
        assertTrue(area.hitWall());

        area.head.head.setXposition(15);
        area.head.head.setYposition(100);
        assertTrue(area.hitWall());

        area.head.head.setXposition(100);
        area.head.head.setYposition(area.getAreaWidth()-15);
        assertTrue(area.hitWall());

        area.head.head.setXposition(100);
        area.head.head.setYposition(15);
        assertTrue(area.hitWall());
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

        area.update();

        area.head.head.setXposition(area.getAreaWidth());
        area.head.head.setYposition(0);

        assertFalse(area.gameOver);
    }
    @Test
    public void hittingItselfWillReturnTrue() throws SQLException {

        area.head.head.setXposition(200);
        area.head.head.setYposition(200);
        area.head.parts.get(5).setXposition(200);
        area.head.parts.get(5).setYposition(200);

        boolean t = area.hitItself();
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

        area.gameOver=true;
        assertTrue(area.gameOver());
    }

}

