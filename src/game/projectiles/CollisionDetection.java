package game.projectiles;

import game.enemies.Enemy;
import game.framework.Vector;

public class CollisionDetection {
    public static boolean checkCollisionLaser(Projectile projectile, Enemy enemy) {
        Vector[] enemyCorners = {
                new Vector(enemy.getLocation().x - (float)enemy.getSize() / 2, enemy.getLocation().y - (float)enemy.getSize() / 2),
                new Vector(enemy.getLocation().x + (float)enemy.getSize() / 2, enemy.getLocation().y - (float)enemy.getSize() / 2),
                new Vector(enemy.getLocation().x + (float)enemy.getSize() / 2, enemy.getLocation().y + (float)enemy.getSize() / 2),
                new Vector(enemy.getLocation().x - (float)enemy.getSize() / 2, enemy.getLocation().y + (float)enemy.getSize() / 2)
        };


        double angleA = projectile.getTargetLocation().angle(projectile.getLocation());
        double angleB = angleA - Math.PI/2;

        float xA = (float)Math.sin(angleB)*projectile.getRadius();
        float xB = (float)Math.cos(angleB)*projectile.getRadius();

        Vector[] projectileCorners = {
                new Vector(projectile.getLocation().x+xB, projectile.getLocation().y+xA),
                new Vector(projectile.getTargetLocation().x+xB,projectile.getTargetLocation().y+xA),
                new Vector(projectile.getTargetLocation().x-xB,projectile.getTargetLocation().y-xA),
                new Vector(projectile.getLocation().x-xB, projectile.getLocation().y-xA)
        };

        Vector[] axes = new Vector[enemyCorners.length + projectileCorners.length];

        for (int i = 0; i < enemyCorners.length; i++) {
            Vector edge = enemyCorners[i].subtract(enemyCorners[(i + 1) % enemyCorners.length]);
            axes[i] = edge.perpendicular();
        }

        for (int i = 0; i < projectileCorners.length; i++) {
            Vector edge = projectileCorners[i].subtract(projectileCorners[(i + 1) % projectileCorners.length]);
            axes[enemyCorners.length + i] = edge.perpendicular();
        }

        for (Vector axis : axes) {
            if (!overlapOnAxis(axis, enemyCorners, projectileCorners)) {
                return false;
            }
        }

        return true;
    }

    public static float[] projectShape(Vector axis, Vector[] shape) {
        float min = Float.MAX_VALUE;
        float max = Float.MIN_VALUE;

        for (Vector vertex : shape) {
            float projection = vertex.dot(axis);
            min = Math.min(min, projection);
            max = Math.max(max, projection);
        }

        return new float[]{min, max};
    }

    public static boolean overlapOnAxis(Vector axis, Vector[] shape1, Vector[] shape2) {
        float[] projection1 = projectShape(axis, shape1);
        float[] projection2 = projectShape(axis, shape2);

        return !(projection1[1] < projection2[0] || projection2[1] < projection1[0]);
    }

    public static boolean checkCollisionNormal(Projectile projectile, Enemy enemy){
        float projLeft = projectile.getLocation().x - projectile.getRadius();
        float projRight = projectile.getLocation().x + projectile.getRadius();
        float projTop = projectile.getLocation().y - projectile.getRadius();
        float projBottom = projectile.getLocation().y + projectile.getRadius();

        float enemyLeft = enemy.getLocation().x - (float)enemy.getSize() / 2;
        float enemyRight = enemy.getLocation().x + (float)enemy.getSize() / 2;
        float enemyTop = enemy.getLocation().y - (float)enemy.getSize() / 2;
        float enemyBottom = enemy.getLocation().y + (float)enemy.getSize() / 2;

        if (projRight >= enemyLeft && projLeft <= enemyRight && projBottom >= enemyTop && projTop <= enemyBottom){
            return true;
        }
        return false;
    }
}
