package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.List;

public class FastEnemy extends Enemy {
    private double currentRotation = 0; // Angle in radians
    private double rotationSpeed = 0.1; // Smooth rotation transition speed

    public FastEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(6)
                .setSegments(segments)
                .setSpeedValue(5.f)
                .setSize(25)
                .setMoneyToGive(7)
                .setColor(new Color(9, 129, 191))  // Changed color to make it look faster
                .setEnemyType(EnemyType.FAST)
                .finalizeBuild(this);
    }

    @Override
    public void update() {
        super.update();

        // Update rotation angle based on direction
        int direction = getSegments().get(getCurrentSegment()).getDirection();
        double targetRotation = 0;

        switch (direction) {
            case 1: // UP
                targetRotation = Math.toRadians(270)+Math.PI/2;
                break;
            case 2: // RIGHT
                targetRotation = Math.toRadians(0) +Math.PI/2;
                break;
            case 3: // DOWN
                targetRotation = Math.toRadians(90)+Math.PI/2;
                break;
            case 4: // LEFT
                targetRotation = Math.toRadians(180)+Math.PI/2;
                break;
        }

        // Smooth the rotation transition
        if (currentRotation != targetRotation) {
            double delta = targetRotation - currentRotation;
            if (Math.abs(delta) > rotationSpeed) {
                currentRotation += Math.signum(delta) * rotationSpeed;
            } else {
                currentRotation = targetRotation;
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        int size = getSize();
        int x = (int) getLocation().x;
        int y = (int) getLocation().y;

        // Apply rotation and draw the enemy with a dynamic shape (e.g., a triangle)
        AffineTransform oldTransform = g.getTransform();
        g.rotate(currentRotation, x, y);

        g.setColor(getColor());

        // Draw a triangle or custom shape to represent speed and agility
        Path2D.Double shape = new Path2D.Double();
        shape.moveTo(x, y- size/2.f);  // Top of the triangle
        shape.lineTo(x + size / 2.f, y + size / 2.f); // Bottom left
        shape.lineTo(x - size / 2.f, y + size / 2.f); // Bottom right
        shape.closePath();

        g.fill(shape);

        // Optionally, add a glowing effect or trail (simple glowing outline for now)
        g.setStroke(new BasicStroke(3));
        g.setColor(new Color(0, 255, 255, 128)); // Semi-transparent glow effect
        g.draw(shape);

        // Reset transformation
        g.setTransform(oldTransform);
        g.setStroke(new BasicStroke());
        g.setColor(Color.black);
    }
}
