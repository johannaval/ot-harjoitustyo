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


    public class SnakePartTest {

        Area area;
        GameService gs;
        SnakePart part;
        SnakeHead head;
        AnchorPane pane;
        GameBoardViewController controller;

        public SnakePartTest() {

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
        public void switchingHeadDirectionSwitchsAllPartsDirection() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            head.switchDirection("DOWN");


            assertEquals(head.parts.get(1).getDirection(), "DOWN");
        }
        @Test
        public void goingUpMakesYSmaller() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            SnakePart part = head.parts.get(1);

            part.goUp();
            part.goUp();
            part.goUp();

            assertEquals(part.newY, 197);
        }


        @Test
        public void goingDownMakesYBigger() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            SnakePart part = head.parts.get(1);

            part.goDown();
            part.goDown();
            part.goDown();

            assertEquals(part.newY, 203);
        }

        @Test
        public void goingLeftMakesXSmaller() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            SnakePart part = head.parts.get(1);

            part.goLeft();
            part.goLeft();
            part.goLeft();

            assertEquals(part.newX, 297);
        }

        @Test
        public void goingRightMakesXBigger() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            SnakePart part = head.parts.get(1);

            part.goRight();
            part.goRight();
            part.goRight();

            assertEquals(part.newX, 303);
        }
        @Test
        public void hittingRightWallWithoutEatingFirstFoodPutsSnakeToLeftWall() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            head.head.setNewYposition(200);
            head.head.setNewXposition(area.getAreaWidth()-15);

            head.parts.get(0).goRight();

            assertEquals(15, head.parts.get(0).newX);

        }

        @Test
        public void hittingLeftWallWithoutEatingFirstFoodPutsSnakeToRightWall() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            head.head.setNewYposition(200);
            head.head.setNewXposition(15);

            head.parts.get(0).goLeft();

            assertEquals(area.getAreaWidth()-30, head.parts.get(0).newX);

        }
        @Test
        public void hittingUpWallWithoutEatingFirstFoodPutsSnakeToDownWall() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            head.head.setNewYposition(15);
            head.head.setNewXposition(200);

            head.parts.get(0).goUp();

            assertEquals(area.getAreaLength()-30, head.parts.get(0).newY);

        }
        @Test
        public void hittingDownWallWithoutEatingFirstFoodPutsSnakeToUpWall() {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            head.head.setNewYposition(area.getAreaLength()-30);
            head.head.setNewXposition(200);

            head.parts.get(0).goDown();

            assertEquals(15, head.parts.get(0).newY);

        }
        }
