
package snakegame.domain;

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


public class FoodTest {

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
    public void afterEatingFoodPointsIncrease() throws SQLException {


        area.addFood();

        int foodX = area.food.getXposition();
        int foodY = area.food.getYposition();

        area.head.head.setNewXposition(foodX);
        area.head.head.setNewYposition(foodY);

        area.update();

        assertEquals(50, area.getPoints());
    }

    @Test
    public void returnTrueIfFoodWasEaten() throws SQLException {

        area.addFood();

        int foodX = area.food.getXposition();
        int foodY = area.food.getYposition();

        area.head.head.setXposition(foodX);
        area.head.head.setYposition(foodY);
        assertTrue(area.ateFood(area.food));

        area.head.head.setXposition(foodX - 1);
        area.head.head.setYposition(foodY - 1);
        assertTrue(area.ateFood(area.food));

        area.head.head.setXposition(foodX - 2);
        area.head.head.setYposition(foodY - 2);
        assertTrue(area.ateFood(area.food));

        area.head.head.setXposition(foodX + 1);
        area.head.head.setYposition(foodY + 1);
        assertTrue(area.ateFood(area.food));

        area.head.head.setXposition(foodX + 2);
        area.head.head.setYposition(foodY + 2);
        assertTrue(area.ateFood(area.food));

        area.head.head.setXposition(foodX + 3);
        area.head.head.setYposition(foodY + 3);
        assertTrue(area.ateFood(area.food));

        area.head.head.setXposition(foodX - 3);
        area.head.head.setYposition(foodY - 3);
        assertTrue(area.ateFood(area.food));

    }

    @Test
    public void ateFoodReturnFalseIfThereIsNoFood() throws SQLException {

        area.food = null;

        assertFalse(area.ateFood(area.food));
    }

    @Test
    public void beforeEatingPointsAreaZero() throws SQLException {


        assertEquals(0, area.getPoints());

    }
}
