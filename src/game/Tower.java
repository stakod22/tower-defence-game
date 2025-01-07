package game;

import java.util.ArrayList;
import java.util.List;

public abstract class Tower implements Drawable{
    private int id;
    private Vector location;
    private Vector target = new Vector(0, 0);
    private int range;
    private int cost;
    private EnemyList enemyList = null;
    private int firerate;
    private boolean willShoot = true;

    public Tower(Vector location,int range,EnemyList enemyList) {
        this.location = location;
        this.range = range;
        this.enemyList = enemyList;
    }

    abstract Projectile shootProjectile(EnemyList enemies);

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
        enemies.inRange(range, location);
        seenEnemies = enemies.getSortedEnemyList("distance");
        if(seenEnemies != null){
            setTarget(seenEnemies.get(0).getLocation());
            willShoot = true;
        }else{
            willShoot = false;
        }
    }

    public void update(EnemyList enemies){
        seeEnemies(enemies);
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
}
