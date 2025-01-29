package game.enemies;

import game.framework.Vector;
import game.path.PathSegment;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SuperBoss100Enemy extends Enemy {
    private final List<Particle> particles = new ArrayList<>();
    private final Random random = new Random();

    public SuperBoss100Enemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(2000)
                .setSegments(segments)
                .setSpeedValue(0.7f)
                .setSize(60) // Increased size for epic feel
                .setMoneyToGive(1000) // Higher reward for defeating
                .setEnemyType(EnemyType.BOSS)
                .finalizeBuild(this);
    }

    @Override
    public void update() {
        super.update();
        // Update particles for the boss's effect
        updateParticles();
        if (isTransformed()) {
            spawnParticles();
        }
    }

    @Override
    public void draw(Graphics2D g) {
        int size = super.getSize();
        int centerX = (int) super.getLocation().x;
        int centerY = (int) super.getLocation().y;

        // Dynamic gradient for main boss body (lava-friendly colors)
        GradientPaint gradient = new GradientPaint(
                centerX - size, centerY - size, Color.DARK_GRAY,
                centerX + size, centerY + size, new Color(255, 0, 0), true);
        g.setPaint(gradient);
        g.fillRect(centerX - size / 2, centerY - size / 2, size, size);

        // Pulsating glow effect (lava orange)
        int glowSize = size / 3;
        float pulse = (float) (Math.sin(System.currentTimeMillis() * 0.005) * 0.5 + 0.5);
        g.setColor(new Color(213, 48, 48,(int) (105 * pulse)));
        g.fillOval(centerX - size / 2 - glowSize, centerY - size / 2 - glowSize, size + 2 * glowSize, size + 2 * glowSize);

        // Intricate inner patterns
        g.setColor(Color.WHITE);
        g.drawRect(centerX - size / 4, centerY - size / 4, size / 2, size / 2);
        for (int i = 0; i < 4; i++) {
            g.drawLine(
                    centerX - size / 4 + i * size / 8, centerY - size / 4,
                    centerX - size / 4 + i * size / 8, centerY + size / 4);
            g.drawLine(
                    centerX - size / 4, centerY - size / 4 + i * size / 8,
                    centerX + size / 4, centerY - size / 4 + i * size / 8);
        }

        // Transformation visuals
        if (isTransformed()) {
            drawTransformedState(g, centerX, centerY, size);
        }

        // Draw particles
        drawParticles(g);

        // Health bar
        drawHealthBar(g, centerX, centerY, size);
        g.setStroke(new BasicStroke());
        g.setColor(Color.BLACK);
    }

    private void drawTransformedState(Graphics2D g, int centerX, int centerY, int size) {
        g.setColor(new Color(255, 0, 0, 180));
        g.fillOval(centerX - size / 2, centerY - size / 2, size, size);

        // Spinning spikes
        int spikeCount = 16;
        double angleOffset = System.currentTimeMillis() * 0.002 % (2 * Math.PI);
        g.setColor(new Color(83, 0, 0));
        g.setStroke(new BasicStroke(2f));
        for (int i = 0; i < spikeCount; i++) {
            double angle = angleOffset + 2 * Math.PI * i / spikeCount;
            int x1 = centerX + (int) (Math.cos(angle) * size / 2);
            int y1 = centerY + (int) (Math.sin(angle) * size / 2);
            int x2 = centerX + (int) (Math.cos(angle) * size * 0.75);
            int y2 = centerY + (int) (Math.sin(angle) * size * 0.75);
            g.drawLine(x1, y1, x2, y2);
        }
        g.setStroke(new BasicStroke(1f));

        // Aura effect
        g.setColor(new Color(204, 0, 0, 80));
        g.drawOval(centerX - size, centerY - size, size * 2, size * 2);
        g.drawOval(centerX - size * 3 / 2, centerY - size * 3 / 2, size * 3, size * 3);
    }

    private void drawHealthBar(Graphics2D g, int centerX, int centerY, int size) {
        int barWidth = size;
        int barHeight = 8;
        int health = getHealth();
        int maxHealth = 2000;
        int healthWidth = (int) ((double) health / maxHealth * barWidth);

        g.setColor(Color.GRAY);
        g.fillRect(centerX - barWidth / 2, centerY - size / 2 - 20, barWidth, barHeight);
        g.setColor(Color.RED);
        g.fillRect(centerX - barWidth / 2, centerY - size / 2 - 20, healthWidth, barHeight);
        g.setColor(Color.WHITE);
        g.drawRect(centerX - barWidth / 2, centerY - size / 2 - 20, barWidth, barHeight);
    }

    private void updateParticles() {
        particles.removeIf(Particle::isExpired);
        for (Particle particle : particles) {
            particle.update();
        }
    }

    private void drawParticles(Graphics2D g) {
        for (Particle particle : particles) {
            particle.draw(g);
        }
    }

    private void spawnParticles() {
        for (int i = 0; i < 5; i++) {
            particles.add(new Particle(super.getLocation(), random));
        }
    }

    private boolean isTransformed() {
        return super.getHealth() < 1000;
    }

    // Inner class for particle effects
    private static class Particle {
        private Vector position;
        private Vector velocity;
        private int lifetime;

        public Particle(Vector origin, Random random) {
            this.position = new Vector(origin.x, origin.y);
            this.velocity = new Vector(random.nextFloat() * 2 - 1, random.nextFloat() * 2 - 1);
            this.lifetime = random.nextInt(60) + 30;
        }

        public void update() {
            position.x += velocity.x;
            position.y += velocity.y;
            lifetime--;
        }

        public void draw(Graphics2D g) {
            int alpha = (int) (255 * (lifetime / 90.0));
            g.setColor(new Color(255, 69, 0, Math.max(alpha, 0)));
            g.fillRect((int) position.x, (int) position.y, 4, 4);
        }

        public boolean isExpired() {
            return lifetime <= 0;
        }
    }
}
