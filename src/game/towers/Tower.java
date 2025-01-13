package game.towers;

import game.framework.Drawable;
import game.projectiles.Projectile;
import game.framework.Vector;
import game.enemies.Enemy;
import game.enemies.EnemyList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower implements Drawable {
    private int id;
    private Vector location;
    private Vector target = new Vector(0, 0);
    private int range;
    private int cost;
    private EnemyList enemyList = null;
    private int firerate;
    private boolean willShoot = false;
    private int shootableFrameCount = 0;
    private int pierce;
    private int damage;
    private Color towerColor;


    public Tower(Vector location,int range,EnemyList enemyList) {
        this.location = location;
        this.range = range;
        this.enemyList = enemyList;
    }

    //Targeting hört nicht auf und sieht nicht den ersten (Beim schnellen Enemy) @Lukas || by Noah
    abstract Projectile shootProjectile();

    public boolean isInRange(Vector position){
        if (location.distanceToOther(position) <= range){
            if (location.distanceToOther(position) == -1){
                return false;
            }
            return true;
        }
        return false;
    }

    private void seeEnemies(EnemyList enemies){
        List<Enemy> seenEnemies;
        seenEnemies = enemies.inRange(range, location).getSortedEnemyList("distance");;
        if (!seenEnemies.isEmpty()){
            setTarget(seenEnemies.get(0).getLocation());
            willShoot = true;
        }else{
            setTarget(null);
            willShoot = false;
        }

    }

    public List<Projectile> update(EnemyList enemies){
        seeEnemies(enemies);
        List<Projectile> projectiles = new ArrayList<>();
        if(willShoot){
            if (shootableFrameCount % firerate == 0){
                projectiles.add(shootProjectile());
            }
            shootableFrameCount++;
        }
        return projectiles;
    }

    @Override
    public void update() {

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

    public Vector getTarget() {
        return target;
    }

    public void setTarget(Vector target) {
        this.target = target;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setFirerate(int firerate) {
        this.firerate = firerate;
    }

    public int getFirerate() {
        return firerate;
    }

    public boolean willShoot() {
        return willShoot;
    }

    public void setPierce(int pierce) {
        this.pierce = pierce;
    }
    public int getPierce() {
        return pierce;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Color getTowerColor() {
        return towerColor;
    }

    public void setTowerColor(Color towerColor) {
        this.towerColor = towerColor;
    }
}
