package game.towers;

import game.framework.Vector;
import game.projectiles.DamageType;
import game.projectiles.Projectile;
import game.projectiles.RayProjectile;

import java.awt.*;

public class LaserTower extends Tower {
    private int rayDuration = 10;
    public LaserTower(Vector location) {
        new Tower.Builder()
                .setLocation(location)
                .setRange(200)
                .setCost(20)
                .setFirerate(100)
                .setPierce(1)
                .setDamage(3)
                .setProjectileSize(5)
                .setColor(new Color(253, 149,  0))
                .finalizeBuild(this);
    }

    @Override
    public Projectile shootProjectile() {
        return new RayProjectile.Builder()
                .buildFrom(super.shootProjectile())
                .setPierce(rayDuration+super.getPierce()*2)
                .setDamageType(DamageType.RAY)
                .setColor(new Color(253, 149, 0))
                .buildRay();
    }
}
