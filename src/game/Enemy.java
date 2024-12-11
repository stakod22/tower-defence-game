package game;

import java.awt.*;

abstract class Enemy implements Drawable{
    private int id;
    private Vector location;
    private Vector speed;
    private int distanceTraveled;

    public Enemy(int id, Vector location, int distanceTraveled) {
        this.id = id;
        this.location = location;
        this.distanceTraveled = distanceTraveled;
    }
    public Enemy(int id, Vector location) {
        this.id = id;
        this.location = location;
        this.distanceTraveled = 0;
    }

    @Override
    public void update() {

    }
    @Override
    public void draw(Graphics2D g) {

    }
}
