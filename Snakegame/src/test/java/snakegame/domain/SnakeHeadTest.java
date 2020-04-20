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
    AnchorPane pane;
    GameBoardViewController controller;

    @Before
    public void setUp() {
        pane = new AnchorPane();
        gs = new GameService(pane, controller);
    }

    @Test
    public void atTheBeginningSnakeHeadGetRightNumberOfSnakeparts() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        assertEquals(50, gs.area.head.parts.size());
    }

    @Test
    public void settingHeadsDirectionItGetsRightDirection() throws SQLException {

        gs.startGame();
        gs.move();
        gs.enterPressed();

        gs.area.head.setDirection("UP");
        assertEquals("UP", gs.area.head.direction);

        gs.area.head.setDirection("LEFT");
        assertEquals("LEFT", gs.area.head.direction);

        gs.area.head.setDirection("RIGHT");
        assertEquals("RIGHT", gs.area.head.direction);

        gs.area.head.setDirection("DOWN");
        assertEquals("DOWN", gs.area.head.direction);
    }
}
