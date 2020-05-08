
package snakegame.domain;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

import javafx.scene.layout.AnchorPane;
import snakegame.ui.GameBoardViewController;


import static org.junit.jupiter.api.Assertions.assertEquals;


public class FoodTest {

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
    public void afterEatingFoodPointsIncrease() {

        gs.area.addFood();

        int foodX = gs.area.food.getXposition();
        int foodY = gs.area.food.getYposition();

        gs.area.head.head.setNewXposition(foodX);
        gs.area.head.head.setNewYposition(foodY);

        gs.area.update();

        assertEquals(50, gs.area.getPoints());
    }

    @Test
    public void foodCanNotBeBlackIfThemeIs1() {

        gs.area.addFood();
        gs.area.food.setColor();

        boolean result = gs.area.food.color != Color.BLACK;

        assertTrue(result);
    }

    @Test
    public void returnTrueIfFoodWasEaten() {

        gs.area.addFood();

        int foodX = gs.area.food.getXposition();
        int foodY = gs.area.food.getYposition();

        for (int i = -9; i <= 9; i++) {

            gs.area.head.head.setXposition(foodX + i);
            gs.area.head.head.setYposition(foodY + i);

            assertTrue(gs.area.ateFood(gs.area.food));
        }
    }

    @Test
    public void ateFoodReturnFalseIfThereIsNoFood() {

        gs.area.food = null;

        assertFalse(gs.area.ateFood(gs.area.food));
    }

    @Test
    public void beforeEatingPointsAreaZero() {

        assertEquals(0, gs.area.getPoints());
    }
}
