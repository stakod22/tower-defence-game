package game.enemies;

import game.framework.Drawable;
import game.path.PathSegment;
import game.framework.Vector;
import game.projectiles.DamageType;

import java.awt.*;
import java.util.List;

public abstract class Enemy implements Drawable {
    static int count = 1;
    private int id;
    private Vector location;
    private Vector speed;
    private int health;
    private int distanceTraveled;
    private int size;
    private int currentMoveWay = 0;
    private int currentSegment = 0;
    private List<PathSegment> segments;
    private int speedValue;
    private int moneyToGive;
    private int damage = 0;
    private DamageType statusEffect = DamageType.NORMAL;
    private int statusDuration = 0;
    private Color color;

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

    public int getSpeedValue() {
        return speedValue;
    }

    public void setSpeedValue(int speedValue) {
        this.speedValue = speedValue;

    }
    public void setRealSpeedValue(int speedValue) {
        this.speedValue = speedValue;
        speed = new Vector(0,speedValue);
    }

    public int getCurrentSegment() {
        return currentSegment;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setCurrentSegment(int currentSegment) {
        this.currentSegment = currentSegment;
    }

    public List<PathSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<PathSegment> segments) {
        this.segments = segments;
    }

    @Override
    public void update() {
        int standardSpeed = speedValue;
        switch(statusEffect){
            case FREEZE:
                speedValue /= 2;
                statusDuration--;
                break;
        }
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
                    health = -9999999;
                    break;
            }
        }
        location.add(speed);
        speedValue = standardSpeed;
        if(statusDuration <= 0){
            statusEffect = DamageType.NORMAL;
        }
        distanceTraveled += speed.x;
        distanceTraveled += speed.y;
    }
    @Override
    public void draw(Graphics2D g) {
        g.setColor(getColor());
        g.fillRect(location.x-size/2,location.y-size/2, size, size);
        switch(statusEffect){
            case FREEZE:
                g.setColor(new Color( 49, 199, 245, 64));
                g.fillRect(location.x-size/2,location.y-size/2, size, size);
                break;
        }
        g.setColor(Color.black);
        g.drawString(""+getId(),location.x,location.y);
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

    public int getMoneyToGive() {
        return moneyToGive;
    }

    public void setMoneyToGive(int moneyToGive) {
        this.moneyToGive = moneyToGive;
    }

    public DamageType getStatusEffect() {
        return statusEffect;
    }

    public void setStatusEffect(DamageType statusEffect) {
        this.statusEffect = statusEffect;
    }

    @Override
    public String toString() {
        String stringer = "";
        stringer += "Enemy: "+ id + "Health: "+ health;
        return stringer;
    }

    public int getStatusDuration() {
        return statusDuration;
    }

    public void setStatusDuration(int statusDuration) {
        this.statusDuration = statusDuration;
    }
}
