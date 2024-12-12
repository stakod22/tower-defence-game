package game;

import java.awt.*;
import java.util.List;

public class StandardTower extends Tower {

    public StandardTower(int id, Vector location,int range) {
        super(id, location,range);
    }


    public Projectile shootProjectile(){
        return null;
    }

    public void getNewTarget(List<Enemy> enemies){
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
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }
}
