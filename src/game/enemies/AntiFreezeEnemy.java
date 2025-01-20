package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;
import game.projectiles.DamageType;
import game.projectiles.StatusEffect;

import java.awt.*;
import java.util.List;

public class AntiFreezeEnemy extends Enemy{
    public AntiFreezeEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(4)
                .setSegments(segments)
                .setSpeedValue(2.f)
                .setSize(30)
                .setMoneyToGive(4)
                .setColor(Color.white)
                .finalizeBuild(this);
    }

    @Override
    public void addStatusEffect(StatusEffect statusEffect) {
        if (statusEffect.damageType != DamageType.FREEZE){
            super.addStatusEffect(statusEffect);
        }
    }
}
