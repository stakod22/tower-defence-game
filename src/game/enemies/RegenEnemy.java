package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;
import game.projectiles.DamageType;
import game.projectiles.StatusEffect;

import java.awt.*;
import java.util.List;

public class RegenEnemy extends Enemy{
    public RegenEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(5)
                .setSegments(segments)
                .setSpeedValue(1.f)
                .setSize(30)
                .setMoneyToGive(6)
                .setColor(Color.red)
                .finalizeBuild(this);
        super.addStatusEffect(new StatusEffect(DamageType.REGEN,125));
    }
}
