package game;

import java.awt.*;

public abstract class Projectile implements Drawable{
    private int id;
    private Vector location;
    private Vector targetLocation;
    private int damage;
    private int radius;
    private int speed = 30;

    public Projectile(Vector location, Vector targetLocation, int damage, int radius) {

        this.location = location;
        this.targetLocation = targetLocation;
        this.damage = damage;
        this.radius = radius;
    }

    abstract public void doDamage(EnemyList enemies);

    public boolean isOnTarget(){
        if (targetLocation.x == location.x && targetLocation.y == location.y){
            return true;
        }
        return false;
    }
    
    public boolean brokeProjectile(){
        if (location.x < 0){
            return true;
        } else if (location.y < 0) {
            return true;
        } else if (location.x > 1500){
            return true;
        } else if (location.y > 1000) {
            return true;
        }else{
            return false;
        }
    }
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
        g.setColor(Color.darkGray);
        g.fillOval(location.x-10, location.y-10, radius*2, radius*2);
        g.setColor(Color.black);
    }
}
