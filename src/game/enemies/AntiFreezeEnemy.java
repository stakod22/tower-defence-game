package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;
import game.projectiles.DamageType;
import game.projectiles.StatusEffect;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.List;

public class AntiFreezeEnemy extends Enemy {

    public AntiFreezeEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(8)
                .setSegments(segments)
                .setSpeedValue(2.2f)
                .setSize(20)  // This will be the size of the square
                .setMoneyToGive(3)
                .setColor(Color.WHITE)  // White to blend in with the "anti" aspect
                .setEnemyType(EnemyType.ANTIFREEZE)
                .finalizeBuild(this);
    }

    @Override
    public void addStatusEffect(StatusEffect statusEffect) {
        // Prevent the freeze status effect from being applied
        if (statusEffect.damageType == DamageType.FREEZE) {
            // Optionally, add a visual effect or message to indicate the freeze has been blocked
            return; // Skip adding freeze effect
        }
        super.addStatusEffect(statusEffect); // Add other status effects as usual
    }

    @Override
    public void draw(Graphics2D g) {
        int size = getSize();
        int x = (int) getLocation().x;
        int y = (int) getLocation().y;

        // Draw a square shape
        g.setColor(getColor());
        g.fill(new Rectangle2D.Double(x - size / 2.f, y - size / 2.f, size, size));

        // Add a faint blue or icy glow around the square to visually represent its "anti-freeze" ability
        g.setStroke(new BasicStroke(4));
        g.setColor(new Color(0, 255, 255, 128));  // A light cyan glow indicating anti-freeze power
        g.draw(new Rectangle2D.Double(x - size / 2.f - 5, y - size / 2.f - 5, size + 10, size + 10)); // Outer glow
        g.setStroke(new BasicStroke());
        g.setColor(Color.black);
    }
}
