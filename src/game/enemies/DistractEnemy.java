package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;
import game.projectiles.DamageType;
import game.projectiles.StatusEffect;

import java.awt.*;
import java.util.List;

public class DistractEnemy extends Enemy{

    public DistractEnemy(Vector location, List<PathSegment> segments) {
        super(location,10, segments, 2);
        super.setSize(30);
        super.setMoneyToGive(6);
        super.setColor(Color.black);
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
