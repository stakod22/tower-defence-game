package game;

import java.util.List;

public abstract class Tower implements Drawable{
    private int id;
    private Vector location;
    private int targetID;
    private int range;

    public Tower(int id, Vector location,int range) {
        this.id = id;
        this.location = location;
        this.range = range;
    }
    abstract Projectile shootProjectile();
    abstract void getNewTarget(List<Enemy> enemies);

    public boolean isInRange(Vector position){

        if (location.distanceToOther(position) <= range){
            if (location.distanceToOther(position) == -1){
                return false;
            }
            return true;
        }
        return false;

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
}
