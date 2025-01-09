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
        enemies.inRange(range, location);
        seenEnemies = enemies.getSortedEnemyList("distance");
        if (seenEnemies.size() > 0){
            if(seenEnemies != null){
                setTarget(seenEnemies.get(0).getLocation());
                willShoot = true;
                //System.out.println(seenEnemies);
                }else{
                setTarget(null);
                willShoot = false;
            }
        }else{
            setTarget(null);
        }
    }

    public List<Projectile> update(EnemyList enemies){
        seeEnemies(enemies);
        List<Projectile> projectiles = new ArrayList<>();
        if(willShoot){
            projectiles.add(shootProjectile());
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
}
