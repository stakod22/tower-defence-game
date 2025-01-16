package game.towers;

import game.projectiles.Projectile;
import game.framework.Vector;

import java.awt.*;

public class StandardTower extends Tower {

    public StandardTower(Vector location) {
        super(location,150);
        setCost(10);
        setFirerate(75);
        setPierce(2);
        setDamage(2);
        setTowerColor(Color.BLUE);
        setTowerType(TowerType.STANDARDTOWER);
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
