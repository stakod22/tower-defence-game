package game.towers;

import game.framework.Vector;
import game.projectiles.Projectile;

import java.awt.*;

public class SniperTower extends Tower {

    public SniperTower(Vector location) {
        super(location,400);
        setCost(60);
        setTowerColor(new Color(64, 163, 79));
        setFirerate(200);
        setPierce(2);
        setDamage(5);

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
                .setSpeed(50)
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
