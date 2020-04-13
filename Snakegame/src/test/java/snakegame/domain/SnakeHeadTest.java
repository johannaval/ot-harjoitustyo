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


    public class SnakeHeadTest {

        Area area;
        GameService gs;
        SnakePart part;
        SnakeHead head;
        AnchorPane pane;
        GameBoardViewController controller;

        public SnakeHeadTest() {

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
        public void snakeHeadGetRightNumberOfSnakeparts() throws SQLException {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            assertEquals(head.parts.size(), 20);
        }
        @Test
        public void settingHeadsDirectionSetRightDirection() throws SQLException {

            AnchorPane pane = new AnchorPane();
            GameService gs = new GameService(pane, controller);
            Area area = new Area(300, 600, pane);

            SnakeHead head = new SnakeHead(20, area);
            area.addNewSnake(head);

            head.setDirection("DOWN");

            assertEquals(head.direction, "DOWN");
        }

    }
