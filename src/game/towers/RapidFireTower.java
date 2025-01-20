package game.towers;

import game.framework.Vector;

import java.awt.*;

public class RapidFireTower extends Tower {
    public RapidFireTower(Vector location) {
        new Tower.Builder()
                .setLocation(location)
                .setRange(75)
                .setCost(20)
                .setFirerate(20)
                .setPierce(1)
                .setDamage(2)
                .setProjectileSize(10)
                .setColor(Color.orange)
                .finalizeBuild(this);
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
