package game.projectiles;

import game.enemies.Enemy;
import game.enemies.EnemyList;
import game.framework.Frame;
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
        boolean hasHitEnemy = false;
        EnemyList enemysToHit = new EnemyList();
        for(Projectile projectile : projectileList){
            if(projectile.getDamageType() == DamageType.RAY){
                for(Enemy enemy : e.getEnemyList()) {
                    if(CollisionDetection.checkCollisionLaser(projectile, enemy)){
                        hasHitEnemy = projectile.getEnemiesHit().contains(enemy);
                        if (!hasHitEnemy){
                            enemysToHit.addEnemy(enemy);
                        }
                    }
                }
            }else{
                for (Enemy enemy : e.getEnemyList()) {
                    if(CollisionDetection.checkCollisionNormal(projectile, enemy)){
                        hasHitEnemy = projectile.getEnemiesHit().contains(enemy);
                        if (!hasHitEnemy){
                            enemysToHit.addEnemy(enemy);
                        }
                    }
                }
            }
            projectile.doDamage(enemysToHit);
            enemysToHit.clear();
        }
        removeDoneProjectiles();
    }
}
