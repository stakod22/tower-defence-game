package game.towers;

import game.framework.Vector;

import java.awt.*;

public class StandardTower extends Tower {

    public StandardTower(Vector location) {
        new Tower.Builder()
                .setLocation(location)
                .setRange(150)
                .setCost(10)
                .setFirerate(75)
                .setPierce(2)
                .setDamage(4)
                .setProjectileSize(10)
                .setColor(Color.BLUE)
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
