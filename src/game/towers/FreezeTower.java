package game.towers;

import game.framework.Vector;
import game.projectiles.DamageType;
import game.projectiles.Projectile;

import java.awt.*;



//NOT WORKING RIGHT NOW


public class FreezeTower extends Tower {
    public FreezeTower(Vector location) {
        super(location,75);
        setTowerColor(Color.CYAN);
        setCost(20);
        setFirerate(75);
        setPierce(1);
        setDamage(1);
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
                    .setDamageType(DamageType.FREEZE)
                    .setEffectDuration(120)
                    .setColor(Color.cyan)
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
