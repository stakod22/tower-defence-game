package game;

import java.awt.*;

abstract class Enemy implements Drawable{
    private int id;
    private Vector location;
    private Vector speed;
    private int health;
    private int distanceTraveled;

    public Enemy(int id, Vector location, int health) {
        this.id = id;
        this.location = location;
        this.distanceTraveled = 0;
        this.health = health;
    }

    @Override
    public void update() {

    }
    @Override
    public void draw(Graphics2D g) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(int distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
