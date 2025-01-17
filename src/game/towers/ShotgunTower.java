package game.towers;

import game.enemies.EnemyList;
import game.framework.Vector;
import game.projectiles.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShotgunTower extends Tower{

    public ShotgunTower(Vector location) {
        super(location,50);
        setCost(35);
        setFirerate(100);
        setPierce(1);
        setDamage(1);
        setTowerColor(new Color(189,166,133));
        setTowerType(TowerType.SHOTGUNTOWER);
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
            }
        }
        super.addShootableFrameCount();
        return projectiles;
    }

    public Projectile shootProjectile(){
        Vector loc = new Vector(super.getLocation().x,super.getLocation().y);
        Vector target = new Vector(super.getTarget().x,super.getTarget().y);
        return new Projectile.Builder()
                .setLocation(loc)
                .setTargetLocation(target)
                .setDamage(super.getDamage())
                .setRadius(10)
                .setPierce(super.getPierce())
                .build();
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
