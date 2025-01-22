package game.enemies;

import game.path.PathSegment;
import game.framework.Vector;

import java.awt.*;
import java.util.List;

public class FastEnemy extends Enemy{

    public FastEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(3)
                .setSegments(segments)
                .setSpeedValue(5.f)
                .setSize(20)
                .setMoneyToGive(7)
                .setColor(Color.blue)
                .setEnemyType(EnemyType.FAST)
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
