package snakegame.domain;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DomainTest {

    Area area;
    GameService gs;
    SnakePart part;
    SnakeHead head;
    AnchorPane pane;
    
    public DomainTest() {

    }
    
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
    public void areaGetRightNumberOfSnakeparts() {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane);
        Area area = new Area(300,600,pane);

        SnakeHead head = new SnakeHead(20,area);
        area.addNewSnake(head);

        assertEquals(area.parts.size(),20);
    }
    @Test
    public void areaHaveRightLength(){

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane);
        Area area = new Area(300,600,pane);

        assertEquals(area.getAreaLength(),300);

    }
    @Test
    public void areaHaveRightWidth(){

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane);
        Area area = new Area(300,600,pane);

        assertEquals(area.getAreaWidth(),600);
    }
    @Test
    public void snakeHeadGetRightNumberOfSnakeparts() {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane);
        Area area = new Area(300,600,pane);

        SnakeHead head = new SnakeHead(20,area);
        area.addNewSnake(head);

        assertEquals(head.parts.size(),20);
    }
}
