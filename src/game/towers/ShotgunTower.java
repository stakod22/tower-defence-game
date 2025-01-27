package game.towers;

import game.enemies.EnemyList;
import game.framework.Vector;
import game.projectiles.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShotgunTower extends Tower{

    public ShotgunTower(Vector location) {
        new Tower.Builder()
                .setLocation(location)
                .setRange(75)
                .setCost(30)
                .setFirerate(80)
                .setPierce(1)
                .setDamage(2)
                .setProjectileSize(10)
                .setColor(new Color(189,166,133))
                .finalizeBuild(this);
    }

    @Override
    public List<Projectile> update(EnemyList enemies) {
        super.seeEnemies(enemies);
        List<Projectile> projectiles = new ArrayList<>();
        if(super.willShoot()){
            if (super.getShootableFrameCount() >= super.getFirerate()){
                projectiles.add(shootProjectile());
                setTarget(targetOffset(15));
                projectiles.add(shootProjectile());
                setTarget(targetOffset(15));
                projectiles.add(shootProjectile());
                setTarget(targetOffset(-45));
                projectiles.add(shootProjectile());
                setTarget(targetOffset(-15));
                projectiles.add(shootProjectile());
                setTarget(targetOffset(30));
                super.setShootableFrameCount(0);
                setShoot(true);
            }
        }
        super.addShootableFrameCount();
        return projectiles;
    }

    public Vector targetOffset(float degree){
        float rad = (float) Math.toRadians(degree);
        float distanceToTarget = (float) getLocation().distanceToOther(getTarget());


        float currentAngle = (float) Math.atan2(
                getTarget().y - getLocation().y,
                getTarget().x - getLocation().x
        );

        currentAngle += rad;

        float x = (float) (Math.cos(currentAngle) * distanceToTarget) + getLocation().x;
        float y = (float) (Math.sin(currentAngle) * distanceToTarget) + getLocation().y;

        return new Vector(x, y);


    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
