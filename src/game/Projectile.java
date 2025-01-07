package game;

import java.awt.*;

public abstract class Projectile implements Drawable{
    private int id;
    private Vector location;
    private Vector targetLocation;
    private int damage;
    private int radius;

    public Projectile(int id, Vector location, Vector targetLocation, int damage, int radius) {
        this.id = id;
        this.location = location;
        this.targetLocation = targetLocation;
        this.damage = damage;
        this.radius = radius;
    }

    abstract public void doDamage(EnemyList enemies);

    public int getDamage() {
        return damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public Vector getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Vector targetLocation) {
        this.targetLocation = targetLocation;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }
}
