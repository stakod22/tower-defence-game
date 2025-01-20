package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;
import game.projectiles.DamageType;
import game.projectiles.StatusEffect;

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
                .finalizeBuild(this);
        super.addStatusEffect(new StatusEffect(DamageType.DISTRACTION,99999));
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
