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
                for(Enemy enemy : e.getEnemyList()){
                    float angle = projectile.getLocation().angle(enemy.getLocation());
                    float height = 0.f;
                    if(angle > 0){
                        height = projectile.getLocation().y;
                    }else if(angle < 0){
                        height = Frame.WINDOWS_HEIGHT - projectile.getLocation().y;
                    }
                        Vector[] enemyCorners = {
                                new Vector(enemy.getLocation().x - enemy.getSize()/2, enemy.getLocation().y - enemy.getSize()/2),
                                new Vector(enemy.getLocation().x + enemy.getSize()/2, enemy.getLocation().y - enemy.getSize()/2),
                                new Vector(enemy.getLocation().x + enemy.getSize()/2, enemy.getLocation().y + enemy.getSize()/2),
                                new Vector(enemy.getLocation().x - enemy.getSize()/2, enemy.getLocation().y + enemy.getSize()/2)
                        };

                        // Parallelogramm-Eckpunkte berechnen
                        Vector[] projectileCorners = {
                                new Vector(projectile.getLocation().x, projectile.getLocation().y),
                                new Vector(
                                        (float)(projectile.getLocation().x + projectile.getRadius() * Math.cos(angle)),
                                        (float)(projectile.getLocation().y + projectile.getRadius() * Math.sin(angle))
                                ),
                                new Vector(
                                        (float)(projectile.getLocation().x + projectile.getRadius() * Math.cos(angle) + height * Math.sin(angle)),
                                        (float)(projectile.getLocation().y + projectile.getRadius() * Math.sin(angle) - height * Math.cos(angle))
                                ),
                                new Vector(
                                        (float) (projectile.getLocation().x + height * Math.sin(angle)),
                                        (float) (projectile.getLocation().y - height * Math.cos(angle))
                                )
                        };

//                        // SAT Kollisionsprüfung
//                        return checkCollisionSAT(squareVertices, parallelogramVertices);

//                    public boolean checkCollision(Vector[] squareVertices, Vector[] parallelogramVertices) {
//                        // Combine edges of both shapes to generate potential separating axes
//                        Vector[] axes = new Vector2D[squareVertices.length + parallelogramVertices.length];
//
//                        // Add square axes
//                        for (int i = 0; i < squareVertices.length; i++) {
//                            Vector edge = squareVertices[i].subtract(squareVertices[(i + 1) % squareVertices.length]);
//                            axes[i] = edge.perpendicular();
//                        }
//
//                        // Add parallelogram axes
//                        for (int i = 0; i < parallelogramVertices.length; i++) {
//                            Vector edge = parallelogramVertices[i].subtract(parallelogramVertices[(i + 1) % parallelogramVertices.length]);
//                            axes[squareVertices.length + i] = edge.perpendicular();
//                        }
//
//                        // Check for overlap on all axes
//                        for (Vector axis : axes) {
//                            if (!overlapOnAxis(axis, squareVertices, parallelogramVertices)) {
//                                // Found a separating axis, no collision
//                                return false;
//                            }
//                        }
//
//                        // All axes overlap, collision detected
//                        return true;
//                    }
//
//// Project a shape onto an axis and check for overlap
//                    private boolean overlapOnAxis(Vector2D axis, Vector2D[] shape1, Vector2D[] shape2) {
//                        float[] projection1 = projectShape(axis, shape1);
//                        float[] projection2 = projectShape(axis, shape2);
//
//                        return !(projection1[1] < projection2[0] || projection2[1] < projection1[0]);
//                    }
//
//// Project a shape onto an axis
//                    private float[] projectShape(Vector axis, Vector[] shape) {
//                        float min = Float.MAX_VALUE;
//                        float max = Float.MIN_VALUE;
//
//                        for (Vector vertex : shape) {
//                            float projection = vertex.dot(axis);
//                            min = Math.min(min, projection);
//                            max = Math.max(max, projection);
//                        }
//
//                        return new float[]{min, max};
//                    }
                }
            }
            for (Enemy enemy : e.getEnemyList()) {
                float projLeft = projectile.getLocation().x - projectile.getRadius();
                float projRight = projectile.getLocation().x + projectile.getRadius();
                float projTop = projectile.getLocation().y - projectile.getRadius();
                float projBottom = projectile.getLocation().y + projectile.getRadius();

                float enemyLeft = enemy.getLocation().x - enemy.getSize() / 2;
                float enemyRight = enemy.getLocation().x + enemy.getSize() / 2;
                float enemyTop = enemy.getLocation().y - enemy.getSize() / 2;
                float enemyBottom = enemy.getLocation().y + enemy.getSize() / 2;

                if (projRight >= enemyLeft && projLeft <= enemyRight && projBottom >= enemyTop && projTop <= enemyBottom) {
                    hasHitEnemy = projectile.getEnemiesHit().contains(enemy);

                    if (!hasHitEnemy) {
                        enemysToHit.addEnemy(enemy);
                    }
                    projectile.doDamage(enemysToHit);
                    enemysToHit.clear();
                }
            }
        }
        removeDoneProjectiles();
    }
}
