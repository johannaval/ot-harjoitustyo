package snakegame.domain;

import javafx.scene.shape.Rectangle;

/**
 * Madon osista vastaava luokka
 */
public class SnakePart extends Rectangle {

    /**
     * Palan uusi X:arvo
     */
    public int newX;
    /**
     * Palan uusi Y:arvo
     */
    public int newY;
    /**
     * Palan X:arvo ennen päivitystä
     */
    public int x;
    /**
     * Palan Y:arvo ennen päivitystä
     */
    public int y;
    private String up = "UP";
    private String down = "DOWN";
    private String right = "RIGHT";
    private String left = "LEFT";
    private String direction = "RIGHT";
    private SnakePart previous;
    private int maxSizeX;
    private int maxSizeY;
    private double speed;
    private Area area;


    /**
     * Konstruktori, joka saa parametreinaan
     * @param x madon x, koordinaatti
     * @param y madon y koordinaatti
     * @param part madon pala
     * @param area pelialueesta vastaava luokka
     */
    public SnakePart(int x, int y, SnakePart part, Area area) {
        super(15, 15);
        this.newX = x;
        this.newY = y;
        setTranslateX(newX);
        setTranslateY(newY);
        previous = part;
        maxSizeX = area.getAreaWidth();
        maxSizeY = area.getAreaLength();
        setArcWidth(10);
        setArcHeight(10);
        this.area = area;

    }

    /**
     * asettaa uudeksi suunnaksi parametrin suunnan
     *
     * @param direction suunta
     */
    public void setDirection(String direction) {

        this.direction = direction;
        switchDirection();
    }

    /**
     * palauttaa madon suunnan
     *
     * @return suunta
     */
    public String getDirection() {

        return this.direction;
    }

    /**
     * Kutsuu metodia, joka vaihtaa madon suuntaa, ja päivittää uuden paikan
     */
    public void switchDirection() {

        this.speed = area.getSnakeSize() / 200;
        this.x = newX;
        this.y = newY;

        if (previous != null) {
            this.newX = previous.x;
            this.newY = previous.y;

        } else {
            moveWithDirection();
        }
        newPosition();
    }

    /**
     * Ohjaa madon menemään suunnan mukaan
     */
    public void moveWithDirection() {

        switch (direction) {
            case "UP":
                goUp();
                break;
            case "LEFT":
                goLeft();
                break;
            case "RIGHT":
                goRight();
                break;
            case "DOWN":
                goDown();
                break;
        }
    }

    /**
     * Päivittää uuden paikan X:llä ja Y:lle
     */
    public void newPosition() {

        setTranslateX(newX);
        setTranslateY(newY);
    }

    /**
     * Suuntaa matoa ylös pienentäen Y arvoa nopeuden mukaan
     */
    public void goUp() {

        newY = (int) (newY - 2 - speed);
        if (newY <= 1) {
            newY = maxSizeY;
        }
    }

    /**
     * Suuntaa matoa alas kasvattaen Y arvoa nopeuden mukaan
     */
    public void goDown() {

        newY = (int) (newY + 2 + speed);
        if (newY >= maxSizeY) {
            newY = 0;
        }
    }

    /**
     * Suuntaa matoa vasemmalle pienentäen X arvoa nopeuden mukaan
     */
    public void goLeft() {
        newX = (int) (newX - 2 - speed);
        if (newX <= 1) {
            newX = maxSizeX;
        }
    }

    /**
     * Suuntaa matoa oikealle kasvattaen X arvoa nopeuden mukaan
     */
    public void goRight() {
        newX = (int) (newX + 2 + speed);
        if (newX >= maxSizeX) {
            newX = 0;
        }
    }

    /**
     * palayttaa X:n arvon
     *
     * @return X
     */
    public int getXposition() {

        return this.x;
    }

    /**
     * palauttaa Y:n arvon
     *
     * @return Y
     */
    public int getYposition() {

        return this.y;
    }

    /**
     * Metodi testejä varten! Asettaa X:n arvoksi parametrina saadun x:n
     *
     * @param x asetettava x
     */
    public void setXposition(Integer x) {

        this.x = x;
    }

    /**
     * Metodi testejä varten! Asettaa Y:n arvoksi parametrina saadun y:n
     *
     * @param y asetettava y
     */
    public void setYposition(Integer y) {

        this.y = y;
    }

    /**
     * Metodi testejä varten! Asettaa X:n arvoksi parametrina saadun x:n
     *
     * @param x asetettava x
     */
    public void setNewXposition(Integer x) {

        this.newX = x;
    }

    /**
     * Metodi testejä varten! Asettaa Y:n arvoksi parametrina saadun y:n
     *
     * @param y asetettava y
     */
    public void setNewYposition(Integer y) {

        this.newY = y;
    }
}
