package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class DistractEnemy extends Enemy {
    private long lastPulseTime = 0;
    private final long pulseInterval = 300; // Milliseconds for pulsing effect
    private boolean isPulsing = false;
    private Color glowColor = new Color(50, 50, 50, 255); // Dark glow color
    private Color coreColor = new Color(0, 0, 0); // Black core for the enemy

    public DistractEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(16)
                .setSegments(segments)
                .setSpeedValue(1.1f)
                .setSize(20)
                .setMoneyToGive(4)
                .setColor(coreColor)  // Black core color
                .setEnemyType(EnemyType.DISTRACTION)
                .finalizeBuild(this);
    }

    @Override
    public void update() {
        super.update();

        // Handle pulsing behavior (alternating between no glow and intense glow)
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastPulseTime > pulseInterval) {
            lastPulseTime = currentTime;
            isPulsing = !isPulsing;
        }

        // Additional behavior: Add movement patterns like random slight direction changes for unpredictability
    }

    @Override
    public void draw(Graphics2D g) {
        int size = getSize();
        int x = (int) getLocation().x;
        int y = (int) getLocation().y;

        // Pulsing effect: alternate between intense glow and normal glow
        if (isPulsing) {
            glowColor = new Color(255, 0, 0);  // Red pulsing glow for more attention
        } else {
            glowColor = new Color(50, 50, 50, 255);  // Darker, subtle glow effect
        }

        // Draw a dark black core for the enemy (main body)
        g.setColor(coreColor);
        g.fill(new Ellipse2D.Double(x - size / 2.f, y - size / 2.f, size, size));

        // Add the glowing effect (pulsing aura effect)
        g.setColor(glowColor);
        g.setStroke(new BasicStroke(5));
        g.draw(new Ellipse2D.Double(x - size / 2.f - 5, y - size / 2.f - 5, size + 10, size + 10)); // Outer glow
        g.setStroke(new BasicStroke());

        // Optionally, add shadowing or haziness behind the enemy to add a darker effect
        g.setColor(new Color(0, 0, 0, 80)); // Semi-transparent black shadow
        g.fill(new Ellipse2D.Double(x - size / 2.f - 8, y - size / 2.f - 8, size + 16, size + 16));

        // Reset color to core color for the inner part
        g.setColor(coreColor);
        g.fill(new Ellipse2D.Double(x - size / 2.f, y - size / 2.f, size, size));
    }

    // Optional: Override or add methods for handling target prioritization
    // Implementing a mechanic where towers always prioritize this enemy can be done in the game engine,
    // typically by modifying tower targeting logic to always select this enemy first.
}
