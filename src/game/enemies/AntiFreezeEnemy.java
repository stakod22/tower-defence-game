package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;
import game.projectiles.DamageType;
import game.projectiles.StatusEffect;

import java.awt.*;
import java.util.List;

public class AntiFreezeEnemy extends Enemy{
    public AntiFreezeEnemy(Vector location, List<PathSegment> segments) {
        super(location,4, segments, 2);
        super.setSize(30);
        super.setMoneyToGive(4);
        super.setColor(Color.white);
    }

    @Override
    public void addStatusEffect(StatusEffect statusEffect) {
        if (statusEffect.damageType != DamageType.FREEZE){
            super.addStatusEffect(statusEffect);
        }
    }
}
