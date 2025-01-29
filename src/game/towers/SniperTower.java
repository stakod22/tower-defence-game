package game.towers;

import game.framework.Vector;
import game.projectiles.Projectile;

import java.awt.*;

public class SniperTower extends Tower {

    public SniperTower(Vector location) {
        new Tower.Builder()
                .setLocation(location)
                .setRange(320)
                .setCost(35)
                .setFirerate(115)
                .setPierce(3)
                .setDamage(9)
                .setProjectileSize(10)
                .setColor(new Color(64, 163, 79))
                .finalizeBuild(this);
    }

    @Override
    public Projectile shootProjectile(){
        return new Projectile.Builder()
                .buildFrom(super.shootProjectile())
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
