package game.projectiles;

import game.enemies.EnemyList;
import game.framework.Vector;
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
            if (projectileList.get(i).brokeProjectile()){
                projectileList.remove(i);
                i--;
            }
            if (projectileList.isEmpty()){
                break;
            }
        }
    }


    public void update(EnemyList e){
        var ref = new Object() {
            boolean hasHitEnemy = false;
        };
        projectileList.forEach((projectile) -> {
            e.getEnemyList().forEach((enemy -> {
                if(projectile.getLocation().x+projectile.getRadius() >= enemy.getLocation().x-enemy.getSize()/2){
                    if(projectile.getLocation().x- projectile.getRadius() <= enemy.getLocation().x+enemy.getSize()/2){
                        if(projectile.getLocation().y+projectile.getRadius() >= enemy.getLocation().y-enemy.getSize()/2){
                            if(projectile.getLocation().y-projectile.getRadius() <= enemy.getLocation().y+enemy.getSize()/2){
                                ref.hasHitEnemy = projectile.getEnemiesHit().contains(enemy);

                                if (!ref.hasHitEnemy){
                                    enemy.getHit(projectile.getDamage());
                                    projectile.hitOnce();
                                    projectile.addEnemyHit(enemy);
                                }
                                ref.hasHitEnemy = false;
                            }
                        }
                    }
                }
            }));
        });
        removeDoneProjectiles();
    }
}
