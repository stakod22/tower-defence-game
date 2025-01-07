package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract class Enemy implements Drawable{
    static int count = 1;
    private int id;
    private Vector location;
    private Vector speed;
    private int health;
    private int distanceTraveled;
    private int size;
    private int currentMoveWay = 0;
    private int currentSegment = 0;
    private List<PathSegment> segments = new ArrayList();
    private int speedValue;
    private int moneyToGive;
    private int damage = 0;

    public Enemy(Vector location, int health, List<PathSegment> segments, int speedValue) {
        location.add(new Vector(25,25));
        this.location = location;
        this.distanceTraveled = 0;
        this.health = health;
        this.segments = segments;
        this.speedValue = speedValue;
        speed = new Vector(0,speedValue);
        id = count;
        count++;
    }

    @Override
    public void update() {
        if(location.y>=0){
            currentMoveWay+=Math.abs(speed.x);
            currentMoveWay+=Math.abs(speed.y);
        }
        if(segments.get(currentSegment).lenght<=currentMoveWay){
            currentSegment++;
            currentMoveWay = 0;

            switch (segments.get(currentSegment).direction){
                case 1:
                    speed = new Vector(0,-speedValue);
                    break;
                case 2:
                    speed = new Vector(speedValue,0);
                    break;
                case 3:
                    speed = new Vector(0,speedValue);
                    break;
                case 4:
                    speed = new Vector(-speedValue,0);
                    break;
                case 5:
                    damage = health;
                    health = 0;
                    break;
            }
        }
        location.add(speed);
        distanceTraveled += speed.x;
        distanceTraveled += speed.y;
    }
    @Override
    public void draw(Graphics2D g) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        System.out.println("enemy: "+ this.id + "was set to "+id);
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

    public void getHit(int damage){
        health -= damage;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getDamage() {
        return damage;
    }
}
