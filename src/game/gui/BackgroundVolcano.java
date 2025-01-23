package game.gui;

import game.framework.Drawable;
import game.framework.Vector;
import game.path.GamePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundVolcano implements Drawable {
    private List<Vector> lavaCracks = new ArrayList<>();
    private List<Vector> moltenRocks = new ArrayList<>();
    private List<Vector> fierySparks = new ArrayList<>();
    private List<Vector> hiddenElements = new ArrayList<>(); // Elements that are hard to notice
    private Vector movingElement = new Vector(0, 400); // A moving element across the screen
    private GamePath path;
    private int tickCounter = 0; // Ticker for slowing down animations
    private Random rand = new Random();

    public BackgroundVolcano(GamePath path) {
        this.path = path;

        // Generate lava cracks (random initial positions, fixed thereafter)
        for (int i = 0; i < 30; i++) {
            lavaCracks.add(new Vector(rand.nextInt(800), rand.nextInt(800)));
        }

        // Generate molten rocks (random initial positions, fixed thereafter)
        for (int i = 0; i < 60; i++) {
            moltenRocks.add(new Vector(rand.nextInt(800), rand.nextInt(800)));
        }

        // Generate fiery sparks (random initial positions, fixed thereafter)
        for (int i = 0; i < 100; i++) {
            fierySparks.add(new Vector(rand.nextInt(800), rand.nextInt(800)));
        }

        // Generate hidden elements (random initial positions, fixed thereafter)
        for (int i = 0; i < 20; i++) {
            hiddenElements.add(new Vector(rand.nextInt(800), rand.nextInt(800)));
        }
    }

    @Override
    public void update() {
        tickCounter++;
        if (tickCounter % 10 == 0) { // Slow down updates (100 FPS -> updates every 10 ticks, i.e., 10 FPS)
            // Move the moving element across the screen
            movingElement.x += 2;
            if (movingElement.x > 800) {
                movingElement.x = -50; // Reset position to loop
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw molten lava background
        drawLavaField(g);

        // Draw lava cracks
        for (Vector crack : lavaCracks) {
            drawLavaCrack(g, (int) crack.x, (int) crack.y);
        }

        // Draw molten rocks
        for (Vector rock : moltenRocks) {
            drawMoltenRock(g, (int) rock.x, (int) rock.y);
        }

        // Draw fiery sparks
        for (Vector spark : fierySparks) {
            drawFierySpark(g, (int) spark.x, (int) spark.y);
        }

        // Draw hidden elements
        for (Vector hidden : hiddenElements) {
            drawHiddenElement(g, (int) hidden.x, (int) hidden.y);
        }

        // Draw moving element
        drawMovingElement(g, (int) movingElement.x, (int) movingElement.y);
    }

    private void drawLavaField(Graphics2D g) {
        g.setColor(new Color(255, 69, 0)); // Bright lava color
        g.fillRect(0, 0, 800, 800);

        g.setColor(new Color(255, 140, 0)); // Molten lava texture
        for (int i = 0; i < 100; i++) {
            int x = (i % 10) * 80;
            int y = (i / 10) * 80;
            g.fillOval(x, y, 6, 6);
        }
    }

    private void drawLavaCrack(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.BLACK);
        for (int i = 0; i < 3; i++) {
            int offsetX = (i - 1) * 5;
            int offsetY = (i - 1) * 5;
            g2d.drawLine(x, y, x + offsetX, y + offsetY);
        }
    }

    private void drawMoltenRock(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(139, 69, 19)); // Dark brown for rock
        g2d.fillOval(x - 10, y - 10, 20, 20);

        g2d.setColor(new Color(255, 99, 71)); // Glow effect
        g2d.fillOval(x - 6, y - 6, 12, 12);
    }

    private void drawFierySpark(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(255, 215, 0)); // Bright yellow for sparks
        g2d.fillOval(x, y, 4, 4);
    }

    private void drawHiddenElement(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(105, 105, 105, 150)); // Subtle, semi-transparent gray
        g2d.fillOval(x - 5, y - 5, 10, 10);
    }

    private void drawMovingElement(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(0, 255, 255)); // Bright cyan
        g2d.fillRect(x, y - 10, 50, 20);
    }
}
