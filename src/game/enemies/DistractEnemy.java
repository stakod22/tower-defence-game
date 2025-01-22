package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;

import java.awt.*;
import java.util.List;

public class DistractEnemy extends Enemy{

    public DistractEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(10)
                .setSegments(segments)
                .setSpeedValue(2.f)
                .setSize(30)
                .setMoneyToGive(6)
                .setColor(Color.black)
                .setEnemyType(EnemyType.DISTRACTION)
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
