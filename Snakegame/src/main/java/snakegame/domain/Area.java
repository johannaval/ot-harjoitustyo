package snakegame.domain;

import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderWidths;

/**
 * Pelialueesta vastaava luokka
 */
public class Area {

    private int length;
    private int width;
    /**
     * Madon palat (vartalo)
     */
    public ArrayList<SnakePart> parts = new ArrayList<>();
    /**
     * Madon pää
     */
    public SnakeHead head;
    /**
     * Pelialueen pohja
     */
    public Pane pane;
    /**
     * Pelaajan pisteet
     */
    public int points;
    /**
     * Pelialueen ruoka
     */
    public Food food;
    /**
     * Boolean arvo, onko peli loppunut
     */
    public boolean gameOver;
    /**
     * Boolean arvo, haluaako pelaaja peliin reunat vai ei
     */
    public boolean withBorders;
    /**
     * Teema, jonka pelaaja valitsi
     */
    public String theme;

    /**
     * Alueen konstruktori, saa parametreinaan pelialueen pituuden, leveyden ja AnchorPanen, eli pelialueen pohjan
     *
     * @param length pelialueen pituus
     * @param width  pelialueen leveys
     * @param pane   pelialueen pohja
     */
    public Area(Integer length, Integer width, Pane pane) {

        this.length = length;
        this.width = width;
        this.pane = pane;
        this.points = 0;
        pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        pane.setMinSize(width, length);
        gameOver = false;
    }

    /**
     * Asettaa pelaajan valitseman teeman mukaisen taustan ja reunat
     */
    public void setTheme() {

        if (this.theme.equals("1")) {

            if (withBorders) {
                pane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
            }
        } else if (this.theme.equals("2")) {

            Image image = new Image(String.valueOf(getClass().getResource("/spacetheme1.jpg")));
            pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            if (withBorders) {
                pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));
            }
        } else if (this.theme.equals("3")) {

            Image image = new Image(String.valueOf(getClass().getResource("/palms.jpg")));
            pane.setBackground(new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));

            if (withBorders) {
                pane.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));
            }
        }
    }

    /**
     * Palauttaa pelialueen leveyden
     *
     * @return leveys
     */
    public int getAreaWidth() {

        return this.width;

    }

    /**
     * Palauttaa madon palojen määrän
     *
     * @return palojen koko
     */
    public int getSnakeSize() {

        return parts.size();
    }

    /**
     * Palauttaa pelialueen pituuden
     *
     * @return pituus
     */
    public int getAreaLength() {

        return this.length;
    }

    /**
     * Saa parametrina madon pään, josta luo madon
     *
     * @param headNew madon pää
     */
    public void addNewSnake(SnakeHead headNew) {

        this.head = headNew;

        for (SnakePart sp : headNew.parts) {
            addParts(sp);
        }
    }

    /**
     * Lisää matoon paloja
     *
     * @param sp pala, joka lisätään
     */
    public void addParts(SnakePart sp) {

        pane.getChildren().add(sp);
        parts.add(sp);

        if (parts.size() > 10) {

            sp.setFill(Color.TRANSPARENT);

            if (theme.equals("1")) {
                sp.setStroke(food.color);
                if (withBorders) {
                    pane.setBorder(new Border(new BorderStroke(food.color, BorderStrokeStyle.SOLID, null, new BorderWidths(15))));
                }
            }
            if (theme.equals("2")) {
                sp.setStroke(Color.ORANGERED);

            }
            if (theme.equals("3")) {
                sp.setFill(Color.BLACK);
                sp.setStroke(Color.DARKOLIVEGREEN);
            }
        }
    }


    /**
     * Metodi päivittää madon sijaintia ja suuntaa, tarkistaa jatkuvasti onko mato syönyt ruoan tai osuuko se itseensä tai reunaan
     */
    public void update() {

        for (SnakePart part : parts) {
            part.switchDirection();
        }
        if (ateFood(food) == true) {
            points = points + 50;
            addFood();
            addNewPart();
        }
        if (hitWall() || hitItself()) {
            gameOver = true;
            return;
        }
    }


    /**
     * Lisää uuden osan matoon
     */
    public void addNewPart() {

        for (int i = 1; i < 15; i++) {

            SnakePart newPart = new SnakePart(head.body.x, head.body.y, head.body, this);
            head.body = newPart;
            addParts(head.body);
        }
        for (int i = 1; i < parts.size() - 20; i++) {
            parts.get(i).setFill(Color.BLACK);
        }
    }

    /**
     * Lisää ruoan pelialueelle satunnaiseen kohtaan
     */
    public void addFood() {

        Random random = new Random();
        int x = random.nextInt(width - 60);
        int y = random.nextInt(length - 60);

        Food newFood = new Food(30 + x, 30 + y, this.theme);
        pane.getChildren().add(newFood);
        pane.getChildren().remove(food);
        food = newFood;

    }

    /**
     * Tarkistaa, osuiko mato reunaan, jos käyttäjä on halunnut pelata reunoilla
     *
     * @return palauttaa true, jos osui, muuten false
     */
    public boolean hitWall() {

        if (withBorders) {
            if ((head.head.getXposition() >= this.width - 30 || head.head.getXposition() <= 15 || head.head.getYposition() <= 15 || head.head.getYposition() >= this.length - 30)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, osuiko mato itseensä
     *
     * @return palauttaa true, jos osui, muuten false
     */
    public boolean hitItself() {

        for (SnakePart part : parts) {
            if (head.head.newX == part.getXposition() && head.head.newY == part.getYposition()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Tarkistaa, osuiko mato pelialueen ruokaan
     *
     * @param food ruoka minkä koordinaatteja tarkistetaan
     * @return true, jos osui, false muuten
     */
    public boolean ateFood(Food food) {

        if (food == null) {
            return false;
        }
        if (Math.abs(food.getXposition() - head.head.getXposition()) < 10 && Math.abs(food.getYposition() - head.head.getYposition()) < 10) {
            return true;
        }
        return false;
    }

    /**
     * Palauttaa pelaajan keräämät pisteet
     *
     * @return pisteet
     */
    public int getPoints() {

        return this.points;
    }

    /**
     * Palauttaa gameOver booleanin arvon
     *
     * @return gameOver arvo
     */
    public boolean gameOver() {

        return gameOver;
    }
}
