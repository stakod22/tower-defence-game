package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;
import game.projectiles.DamageType;
import game.projectiles.StatusEffect;

import java.awt.*;
import java.util.List;

public class RegenEnemy extends Enemy{
    public RegenEnemy(Vector location, List<PathSegment> segments) {
        super(location,5, segments, 1);
        super.setSize(30);
        super.setMoneyToGive(6);
        super.setColor(Color.red);
        super.addStatusEffect(new StatusEffect(DamageType.REGEN,225));
    }

}
