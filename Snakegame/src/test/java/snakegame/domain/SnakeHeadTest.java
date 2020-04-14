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


public class SnakeHeadTest {

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
    public void atTheBeginningSnakeHeadGetRightNumberOfSnakeparts() throws SQLException {

        assertEquals(20, head.parts.size());
    }

    @Test
    public void settingHeadsDirectionItGetsRightDirection() throws SQLException {

        head.setDirection("UP");
        assertEquals("UP", head.direction);

        head.setDirection("LEFT");
        assertEquals("LEFT", head.direction);

        head.setDirection("RIGHT");
        assertEquals("RIGHT", head.direction);

        head.setDirection("DOWN");
        assertEquals("DOWN", head.direction);
    }
}
