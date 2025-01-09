package game.projectiles;

import game.enemies.Enemy;
import game.framework.Drawable;
import game.framework.Vector;
import game.enemies.EnemyList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Projectile implements Drawable {
    private int id;
    private Vector location;
    private Vector targetLocation;
    private int damage;
    private int radius;
    private int speed = 35;
    private int pierce = 0;
    private double angle;
    private String damageType = "default";
    private List<Enemy> enemiesHit = new ArrayList<>();
    private Color color = Color.BLACK;


    public Projectile(Vector location, Vector targetLocation, int damage, int radius, int pierce) {
        init(location, targetLocation, damage, radius, pierce);
    }

    public Projectile(Vector location, Vector targetLocation, int damage, int radius, int pierce, String damageType, Color color) {
        init(location, targetLocation, damage, radius, pierce);
        setDamageType(damageType);
        setColor(color);
    }


    public Projectile(Vector location, Vector targetLocation, int damage, int radius, int pierce, int speed) {
        init(location, targetLocation, damage, radius, pierce);
        this.speed = speed;
        damageType = "default";
    }

    public Projectile(Vector location, Vector targetLocation, int damage, int radius, int pierce, int speed, String damageType) {
        init(location, targetLocation, damage, radius, pierce);
        this.speed = speed;
        this.damageType = damageType;
    }

    public void init(Vector location, Vector targetLocation, int damage, int radius, int pierce){
        this.location = location;
        this.targetLocation = targetLocation;
        this.damage = damage;
        this.radius = radius;
        float x1 = targetLocation.x - location.x;
        float y1 = targetLocation.y - location.y;
        this.pierce = pierce;

        angle = Math.atan2(y1, x1);
    }

    abstract public void doDamage(EnemyList enemies);

    
    public boolean brokeProjectile(){
        if (location.x < 0){
            return true;
        } else if (location.y < 0) {
            return true;
        } else if (location.x > 1500){
            return true;
        } else if (location.y > 1000) {
            return true;
        } else if(pierce == 0){
            return true;
        } else{
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
    public void addEnemyHit(Enemy e){
        enemiesHit.add(e);
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

    public List<Enemy> getEnemiesHit() {
        return enemiesHit;
    }

    public void setDidDamage(int didDamage) {
        this.pierce = pierce + didDamage;
    }

    public void hitOnce(){
        pierce--;
    }

    @Override
    public void update() {
        float x2 = (float) (speed * Math.cos(angle));
        float y2 = (float) (speed * Math.sin(angle));

        location.x += x2;
        location.y += y2;
    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.darkGray);
        g.fillOval(location.x-10, location.y-10, radius*2, radius*2);
        g.setColor(Color.black);
    }

    public String getDamageType() {
        return damageType;
    }

    public void setDamageType(String damageType) {
        this.damageType = damageType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
