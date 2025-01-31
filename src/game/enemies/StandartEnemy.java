package game.enemies;

import game.path.PathSegment;
import game.framework.Vector;

import java.awt.*;
import java.util.List;

public class StandartEnemy extends Enemy{

    public StandartEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(7)
                .setSegments(segments)
                .setSpeedValue(1.f)
                .setSize(30)
                .setMoneyToGive(3)
                .setColor(Color.cyan)
                .setEnemyType(EnemyType.STANDARD)
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
