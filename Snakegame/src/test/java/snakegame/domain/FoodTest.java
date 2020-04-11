
package snakegame.domain;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.AnchorPane;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import snakegame.ui.GameBoardViewController;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FoodTest {

    Area area;
    GameService gs;
    SnakePart part;
    SnakeHead head;
    AnchorPane pane;
    GameBoardViewController controller;

    public FoodTest() {

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
    public void afterEatingFoodPointsIncrease() {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);

        area.addFood();

        int foodX = area.food.getXposition();
        int foodY = area.food.getYposition();

        area.head.head.setNewXposition(foodX);
        area.head.head.setNewYposition(foodY);

        area.update();

        assertEquals(50, area.getPoints());
    }

    @Test
    public void returnTrueIfFoodWasEaten() {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);

        area.addFood();

        int foodX = area.food.getXposition();
        int foodY = area.food.getYposition();

        area.head.head.setXposition(foodX);
        area.head.head.setYposition(foodY);

        assertTrue(area.ateFood(area.food));
    }

    @Test
    public void ateFoodReturnFalseIfThereIsNoFood() {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);
        area.food = null;

        assertFalse(area.ateFood(area.food));
    }

    @Test
    public void beforeEatingPointsAreaZero() {

        AnchorPane pane = new AnchorPane();
        GameService gs = new GameService(pane, controller);
        Area area = new Area(300, 600, pane);

        SnakeHead head = new SnakeHead(20, area);
        area.addNewSnake(head);

        assertEquals(0, area.getPoints());

    }
}
