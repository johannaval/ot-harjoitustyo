
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

    }

    @Test
    public void afterEatingFoodPointsIncrease() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.addFood();

        int foodX = gs.area.food.getXposition();
        int foodY = gs.area.food.getYposition();

        gs.area.head.head.setNewXposition(foodX);
        gs.area.head.head.setNewYposition(foodY);

        gs.area.update();

        assertEquals(50, gs.area.getPoints());
    }

    @Test
    public void returnTrueIfFoodWasEaten() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.addFood();

        int foodX = gs.area.food.getXposition();
        int foodY = gs.area.food.getYposition();

        gs.area.head.head.setXposition(foodX);
        gs.area.head.head.setYposition(foodY);
        assertTrue(gs.area.ateFood(gs.area.food));

        gs.area.head.head.setXposition(foodX - 1);
        gs.area.head.head.setYposition(foodY - 1);
        assertTrue(gs.area.ateFood(gs.area.food));

        gs.area.head.head.setXposition(foodX - 2);
        gs.area.head.head.setYposition(foodY - 2);
        assertTrue(gs.area.ateFood(gs.area.food));

        gs.area.head.head.setXposition(foodX + 1);
        gs.area.head.head.setYposition(foodY + 1);
        assertTrue(gs.area.ateFood(gs.area.food));

        gs.area.head.head.setXposition(foodX + 2);
        gs.area.head.head.setYposition(foodY + 2);
        assertTrue(gs.area.ateFood(gs.area.food));

        gs.area.head.head.setXposition(foodX + 3);
        gs.area.head.head.setYposition(foodY + 3);
        assertTrue(gs.area.ateFood(gs.area.food));

        gs.area.head.head.setXposition(foodX - 3);
        gs.area.head.head.setYposition(foodY - 3);
        assertTrue(gs.area.ateFood(gs.area.food));

    }

    @Test
    public void ateFoodReturnFalseIfThereIsNoFood() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.food = null;

        assertFalse(gs.area.ateFood(gs.area.food));
    }

    @Test
    public void beforeEatingPointsAreaZero() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertEquals(0, gs.area.getPoints());

    }
}
