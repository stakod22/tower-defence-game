package game.towers;

import game.framework.Vector;
import game.projectiles.DamageType;
import game.projectiles.Projectile;
import game.projectiles.RayProjectile;

import java.awt.*;

public class LaserTower extends Tower {
    private int rayDuration;
    public LaserTower(Vector location) {
        super(location,250);
        setTowerColor(Color.CYAN);
        setCost(20);
        setFirerate(100);
        setPierce(1);
        rayDuration = 10;
        setDamage(3);
        setTowerColor(new Color(253, 149,  0));
    }

    @Override
    public Projectile shootProjectile() {
        Vector loc = new Vector(super.getLocation().x,super.getLocation().y);
        Vector target = new Vector(super.getTarget().x,super.getTarget().y);
        return new RayProjectile.Builder()
                .setLocation(loc)
                .setTargetLocation(target)
                .setDamage(super.getDamage())
                .setRadius(5)
                .setPierce(rayDuration + super.getPierce()*10)
                .setDamageType(DamageType.RAY)
                .setEffectDuration(rayDuration + super.getPierce()*10)
                .setColor(new Color(253, 149,  0))
                .buildRay();
    }
}
