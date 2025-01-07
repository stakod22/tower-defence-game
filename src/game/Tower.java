package game;

import java.util.List;

public abstract class Tower implements Drawable{
    private int id;
    private Vector location;
    private int targetID = -2;
    private int range;
    private int cost;
    private EnemyList enemyList = null;

    public Tower(Vector location,int range,EnemyList enemyList) {
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
    public void getNewTarget(EnemyList enemies){
        int furthestAhead = -1;
        if (enemies != null){
            for (int i = 0; i < enemies.getEnemyList().size(); i++) {
                if (furthestAhead != -1){
                    if (enemies.getEnemyById(furthestAhead) == null){
                        furthestAhead = enemies.getEnemyList().get(i).getId();
                    }else{
                        if (enemies.getEnemyById(furthestAhead).getDistanceTraveled() < enemies.getEnemyList().get(i).getDistanceTraveled()){
                            furthestAhead = enemies.getEnemyList().get(i).getId();
                        }
                    }
                }else{
                    furthestAhead = enemies.getEnemyList().get(i).getId();
                }
            }

        }

        if (furthestAhead != -1){
            targetID = furthestAhead;
        }
        System.out.println("Furthest Ahead: "+furthestAhead);

    }

    public void updateEnemy(EnemyList enemyList2){
        this.enemyList = new EnemyList();
        for (int i = 0; i < enemyList2.getEnemyList().size(); i++) {
            if (isInRange(enemyList2.getEnemyList().get(i).getLocation())){
                this.enemyList.addEnemy(enemyList2.getEnemyList().get(i));
            }
        }
    }

    @Override
    public void update() {
        getNewTarget(enemyList);

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
