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

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AreaTest {
    Area area;
    GameService gs;
    SnakePart part;
    SnakeHead head;
    AnchorPane pane;
    GameBoardViewController controller;

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {

    }

    @AfterEach
    public void tearDown() {
    }

    @Test
    public void areaGetRightNumberOfSnakeparts() throws SQLException {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);

        assertEquals(area.parts.size(), 20);
    }

    @Test
    public void areaHaveRightLength() throws SQLException {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        assertEquals(area.getAreaLength(), 300);

    }

    @Test
    public void areaHaveRightWidth() throws SQLException {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        assertEquals(area.getAreaWidth(), 600);
    }

    @Test
    public void notHittingTheWallWillContinueTheGame() throws SQLException {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);

        area.head.head.setXposition(150);
        area.head.head.setYposition(200);

        System.out.println(area.head.head.getXposition());
        System.out.println(area.head.head.getYposition());

        assertFalse(area.hitWall());
    }

    @Test
    public void hittingTheWallWillReturnTrue() throws SQLException {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);

        area.head.head.setXposition(area.getAreaWidth()-15);
        area.head.head.setYposition(100);


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

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);

        area.update();

        area.head.head.setXposition(area.getAreaWidth());
        area.head.head.setYposition(0);

        System.out.println(area.head.head.getXposition());
        System.out.println(area.head.head.getYposition());

        assertFalse(area.gameOver);
    }
}

