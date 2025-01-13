package game.towers;

import game.framework.Vector;
import game.projectiles.Projectile;

import java.awt.*;

public class RapidFireTower extends Tower {
    public RapidFireTower(Vector location) {
        super(location,75);
        setTowerColor(Color.orange);
        setCost(30);
        setFirerate(20);
        setPierce(1);
        setDamage(2);
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
