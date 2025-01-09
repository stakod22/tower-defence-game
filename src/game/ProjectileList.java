package game;

import java.util.ArrayList;
import java.util.List;

public class ProjectileList {
    private List<Projectile> projectileList = new ArrayList<>();

    public void addProjectile(Projectile projectile){
        projectileList.add(projectile);
    }
    public void addAllProjectiles(List<Projectile> projectiles){
        projectileList.addAll(projectiles);
    }

    public List<Projectile> getProjectileList() {
        return projectileList;
    }

    public void setProjectileList(List<Projectile> projectileList) {
        this.projectileList = projectileList;
    }

    public void removeDoneProjectiles(){
        for (int i = 0; i < projectileList.size(); i++) {
            if (projectileList.get(i).isOnTarget()){
                projectileList.remove(i);
                i--;
            }
        }
    }


    public void update(EnemyList e){
        projectileList.forEach((projectile) -> {
            e.getEnemyList().forEach((enemy -> {
                if(projectile.getLocation().distanceToOther(enemy.getLocation()) <= projectile.getRadius()){
                    enemy.getHit(projectile.getDamage());
                }
            }));

        });

    }
}
