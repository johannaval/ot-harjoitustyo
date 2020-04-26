
package snakegame.domain;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import javafx.scene.layout.AnchorPane;
import snakegame.ui.GameBoardViewController;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FoodTest {

    GameService gs;
    AnchorPane pane;
    GameBoardViewController controller;


    @Before
    public void setUp() {
        pane = new AnchorPane();
        controller = new GameBoardViewController();
        controller.borders=true;
        controller.theme="1";
        gs = new GameService(pane, controller);

    }

    @Test
    public void afterEatingFoodPointsIncrease(){

        gs.addGameArea();
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
    public void foodIsColorfullWithTheme1(){

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        gs.area.addFood();
        gs.area.food.setColor();

        boolean result = gs.area.food.color!=Color.BLACK;

        assertTrue(result);
    }

    @Test
    public void returnTrueIfFoodWasEaten() {

        gs.addGameArea();
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
    public void ateFoodReturnFalseIfThereIsNoFood()  {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        gs.area.food = null;

        assertFalse(gs.area.ateFood(gs.area.food));
    }

    @Test
    public void beforeEatingPointsAreaZero() {

        gs.addGameArea();
        gs.move();
        gs.enterPressed();

        assertEquals(0, gs.area.getPoints());

    }
}
