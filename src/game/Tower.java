package game;

import java.util.List;

public abstract class Tower implements Drawable{
    private int id;
    private Vector location;
    private int targetID;
    private int range;
    private int cost;
    private EnemyList enemyList = null;

    public Tower(int id, Vector location,int range,EnemyList enemyList) {
        this.id = id;
        this.location = location;
        this.range = range;
        this.enemyList = enemyList;
    }

    abstract Projectile shootProjectile(List<Vector> enemyLocation);
    public boolean isInRange(Vector position){

        if (location.distanceToOther(position) <= range){
            if (location.distanceToOther(position) == -1){
                return false;
            }
            return true;
        }
        return false;

    }
    public void getNewTarget(List<Enemy> enemies){
        if (enemies == null){
            return;
        }
        int furthestAhead = -1;
        for (int i = 0; i < enemies.size(); i++) {
            if (isInRange(enemies.get(i).getLocation())){
                if (furthestAhead != -1){
                    if (enemies.get(furthestAhead).getDistanceTraveled() < enemies.get(i).getDistanceTraveled()){
                        furthestAhead = i;
                    }
                }else{
                    furthestAhead = i;
                }

            }
        }
        targetID = furthestAhead;
    }

    public void updateEnemy(EnemyList enemyList){
        this.enemyList = enemyList;
    }

    @Override
    public void update() {
        getNewTarget(enemyList.getEnemyList());

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

    public int getTargetID() {
        return targetID;
    }

    public void setTargetID(int targetID) {
        this.targetID = targetID;
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
}
