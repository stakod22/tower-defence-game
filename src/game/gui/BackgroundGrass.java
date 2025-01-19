package game.gui;

import game.framework.Drawable;
import game.framework.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundGrass implements Drawable {
    private List<Vector> flowers = new ArrayList<>();
    private List<Vector> trees = new ArrayList<>();
    private List<Vector> grassTextures = new ArrayList<>();
    private List<Vector> bushes = new ArrayList<>();
    private List<Vector> rocks = new ArrayList<>();
    private List<Vector> cloudShadows = new ArrayList<>();
    private Random rand = new Random();

    public BackgroundGrass() {
        // Generate random flowers
        generateElements(flowers, 40, 16, 16);

        // Generate random trees
        generateElements(trees, 20, 30, 30);

        // Generate random grass texture spots (doesn't need collision detection)
        for (int i = 0; i < rand.nextInt(80) + 100; i++) {
            grassTextures.add(new Vector(rand.nextInt(800), rand.nextInt(800)));
        }

        // Generate random bushes
        generateElements(bushes, 20, 30, 20);

        // Generate random rocks
        generateElements(rocks, 20, 20, 10);

        // Generate cloud shadows (clouds are large but scattered)
        generateElements(cloudShadows, 10, 100, 50);

    }

    @Override
    public void update() {
        // Move cloud shadows slowly to create a dynamic effect
        for (Vector cloud : cloudShadows) {
            cloud.x += 0.5;
            if (cloud.x > 690) {
                cloud.x = -100; // Reset cloud shadow position
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw grassy field with texture
        drawGrassyField(g);

        // Draw trees
        for (Vector tree : trees) {
            drawTree(g, (int) tree.x, (int) tree.y);
        }

        // Draw flowers
        for (Vector flower : flowers) {
            drawFlower(g, flower);
        }

        // Draw bushes
        for (Vector bush : bushes) {
            drawBush(g, (int) bush.x, (int) bush.y);
        }

        // Draw rocks
        for (Vector rock : rocks) {
            drawRock(g, (int) rock.x, (int) rock.y);
        }

        // Draw cloud shadows


    }

    private void drawGrassyField(Graphics2D g) {
        g.setColor(new Color(138, 255, 132));
        g.fillRect(0, 0, 800, 800);

        g.setColor(new Color(123, 240, 115));
        for (Vector texture : grassTextures) {
            g.fillOval((int) texture.x, (int) texture.y, 5, 5);
        }
    }

    private void drawFlower(Graphics2D g2d, Vector flower) {
        int flowerType = flowers.indexOf(flower) % 3;
        int x = (int) flower.x;
        int y = (int) flower.y;

        switch (flowerType) {
            case 0 -> drawDaisy(g2d, x, y);
            case 1 -> drawTulip(g2d, x, y);
            case 2 -> drawSunflower(g2d, x, y);
        }
    }

    private void drawDaisy(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.fillOval(x - 8, y - 8, 16, 16); // Petals
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 4, y - 4, 8, 8); // Center
    }

    private void drawTulip(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.RED);
        g2d.fillOval(x - 6, y - 10, 12, 12); // Tulip bloom
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(x - 1, y, 2, 8); // Stem
    }

    private void drawSunflower(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(218, 165, 32)); // Sunflower petals
        g2d.fillOval(x - 10, y - 10, 20, 20);
        g2d.setColor(new Color(139, 69, 19)); // Sunflower center
        g2d.fillOval(x - 5, y - 5, 10, 10);
    }

    private void drawTree(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(139, 69, 19)); // Trunk
        g2d.fillRect(x - 5, y, 10, 30);
        g2d.setColor(new Color(34, 139, 34)); // Leaves
        g2d.fillOval(x - 15, y - 20, 30, 30);
    }

    private void drawBush(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillOval(x - 15, y - 10, 30, 20);
    }

    private void drawRock(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.GRAY);
        g2d.fillOval(x - 10, y - 5, 20, 10);
    }

    public void drawCloudShadow(Graphics2D g2d) {
        for (Vector cloud : cloudShadows) {
            int x = (int)cloud.x;
            int y = (int)cloud.y;
            g2d.setColor(new Color(0, 0, 0, 50)); // Semi-transparent black for shadow
            g2d.fillOval(x, y, 100, 50);
        }
    }

    private void generateElements(List<Vector> elements, int count, int width, int height) {
        for (int i = 0; i < count; i++) {
            Vector position;
            do {
                position = new Vector(rand.nextInt(800 - width), rand.nextInt(800 - height));
            } while (isOverlapping(position, elements, width, height));
            elements.add(position);
        }
    }

    private boolean isOverlapping(Vector position, List<Vector> existingElements, int width, int height) {
        for (Vector other : existingElements) {
            if (Math.abs(position.x - other.x) < width && Math.abs(position.y - other.y) < height) {
                return true; // Overlapping
            }
        }
        return false;
    }

    // Inner class for buildings
    private static class Building {
        int x, y, width, height;
        Color color;

        public Building(int x, int y, int width, int height, Color color) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.color = color;
        }

        public void draw(Graphics2D g) {
            g.setColor(color);
            g.fillRect(x, y, width, height);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(x, y, width, height); // Outline
        }
    }
}
