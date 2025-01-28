package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;

import java.awt.*;
import java.util.List;

public class RegenEnemy extends Enemy{
    private int regenTimer = 100;
    public RegenEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(10)
                .setSegments(segments)
                .setSpeedValue(2.f)
                .setSize(30)
                .setMoneyToGive(6)
                .setColor(Color.red)
                .setEnemyType(EnemyType.REGENERATION)
                .finalizeBuild(this);

    }

    @Override
    public void update() {
        super.update();
        if (regenTimer == 0){
            regenTimer = 75;
            setHealth(getHealth()+1);
        }
        regenTimer--;
    }
}
