package game.projectiles;

import game.enemies.EnemyList;
import game.framework.Frame;
import game.framework.Vector;

import java.awt.*;
import java.awt.geom.Path2D;

public class RayProjectile extends Projectile {
    public RayProjectile() {
        super.setDamageType(DamageType.RAY);
    }

    @Override
    public void doDamage(EnemyList enemies) {
        super.doDamage(enemies);
        super.setPierce(super.getPierce()-1);
    }

    @Override
    public void hitOnce() {

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(new Color(253, 149,  0));
        Path2D parallelogram;
        parallelogram = new Path2D.Double();

        double angleA = getTargetLocation().angle(getLocation());
        double angleB = angleA - Math.PI/2;

        float xA = (float)Math.sin(angleB)*getRadius();
        float xB = (float)Math.cos(angleB)*getRadius();

        parallelogram.moveTo((int)getLocation().x+xB,(int)getLocation().y+xA);
        parallelogram.lineTo((int)(getTargetLocation().x+xB),(int)getTargetLocation().y+xA);
        parallelogram.lineTo((int)getTargetLocation().x-xB,(int)getTargetLocation().y-xA);
        parallelogram.lineTo((int)getLocation().x-xB, (int)getLocation().y-xA);
        parallelogram.closePath();

        g.fill(parallelogram);

        g.setColor(Color.black);
    }
}
