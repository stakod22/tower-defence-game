package game.towers;

import game.enemies.EnemyList;
import game.framework.Vector;
import game.projectiles.Projectile;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShotgunTower extends Tower{

    public ShotgunTower(Vector location) {
        super(location,150);
        setCost(20);
        setFirerate(120);
        setPierce(1);
        setDamage(1);
        setTowerColor(Color.ORANGE);
        setTowerType(TowerType.SHOTGUNTOWER);
    }

    @Override
    public List<Projectile> update(EnemyList enemies) {
        super.seeEnemies(enemies);
        List<Projectile> projectiles = new ArrayList<>();
        if(super.willShoot()){
            if (super.getShootableFrameCount() >= super.getFirerate()){
                projectiles.add(shootProjectile());
                getTarget().add(new Vector(15,15));
                projectiles.add(shootProjectile());
                getTarget().add(new Vector(15,15));
                projectiles.add(shootProjectile());
                getTarget().add(new Vector(-45,-45));
                projectiles.add(shootProjectile());
                getTarget().add(new Vector(-15,-15));
                projectiles.add(shootProjectile());
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

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
