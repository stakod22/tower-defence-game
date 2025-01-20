package game.towers;

import game.framework.Vector;
import game.projectiles.DamageType;
import game.projectiles.Projectile;

import java.awt.*;


public class FreezeTower extends Tower {
    public FreezeTower(Vector location) {
        new Tower.Builder()
                .setLocation(location)
                .setRange(75)
                .setCost(20)
                .setFirerate(25)
                .setPierce(1)
                .setDamage(1)
                .setProjectileSize(10)
                .setColor(Color.CYAN)
                .finalizeBuild(this);
    }


    public Projectile shootProjectile(){
        return new Projectile.Builder()
                .buildFrom(super.shootProjectile())
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
