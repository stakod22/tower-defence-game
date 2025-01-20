package game.gui;

import game.framework.Drawable;
import game.framework.Vector;
import game.path.GamePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundGrass implements Drawable {
    private List<Vector> flowers = new ArrayList<>();
    private List<Vector> trees = new ArrayList<>();
    private List<Vector> bushes = new ArrayList<>();
    private List<Vector> rocks = new ArrayList<>();
    private List<Vector> grassTextures = new ArrayList<>();
    private List<Vector> cloudShadows = new ArrayList<>();
    private Random rand = new Random();
    private GamePath path;

    public BackgroundGrass(GamePath path) {
        this.path = path;

        // Generate flowers, trees, bushes, rocks with clustering
        generateClusteredElements(flowers, 40, 16, 16, 4); // Flowers in 4x4 clusters
        generateClusteredElements(trees, 20, 30, 30, 6); // Trees in 6x6 clusters
        generateClusteredElements(bushes, 20, 20, 20, 5); // Bushes in 5x5 clusters
        generateClusteredElements(rocks, 20, 20, 10, 5); // Rocks in 5x5 clusters

        // Generate grass textures (random, non-clustered)
        for (int i = 0; i < 150; i++) {
            grassTextures.add(new Vector(rand.nextInt(800), rand.nextInt(800)));
        }

        // Generate cloud shadows (scattered, large objects)
        generateClusteredElements(cloudShadows, 10, 100, 50, 8);
    }

    @Override
    public void update() {
        // Move cloud shadows slowly
        for (Vector cloud : cloudShadows) {
            cloud.x += 0.5;
            if (cloud.x > 800) {
                cloud.x = -100; // Reset cloud shadow position
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw grassy field
        drawGrassyField(g);

        // Draw flowers
        for (Vector flower : flowers) {
            drawFlower(g, flower);
        }

        // Draw trees
        for (Vector tree : trees) {
            drawTree(g, (int) tree.x, (int) tree.y);
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
        for (Vector cloud : cloudShadows) {
            drawCloudShadow(g, (int) cloud.x, (int) cloud.y);
        }
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
        g2d.fillOval(x - 8, y - 8, 16, 16);
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 4, y - 4, 8, 8);
    }

    private void drawTulip(Graphics2D g2d, int x, int y) {
        g2d.setColor(Color.RED);
        g2d.fillOval(x - 6, y - 10, 12, 12);
        g2d.setColor(new Color(34, 139, 34));
        g2d.fillRect(x - 1, y, 2, 8);
    }

    private void drawSunflower(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(218, 165, 32));
        g2d.fillOval(x - 10, y - 10, 20, 20);
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillOval(x - 5, y - 5, 10, 10);
    }

    private void drawTree(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(139, 69, 19));
        g2d.fillRect(x - 5, y, 10, 30);
        g2d.setColor(new Color(34, 139, 34));
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

    private void drawCloudShadow(Graphics2D g2d, int x, int y) {
        g2d.setColor(new Color(0, 0, 0, 50));
        g2d.fillOval(x, y, 100, 50);
    }

    private void generateClusteredElements(List<Vector> elements, int count, int width, int height, int clusterSize) {
        int gridSize = 800 / clusterSize; // Divide the field into a grid
        for (int i = 0; i < count; i++) {
            Vector position;
            do {
                int clusterX = rand.nextInt(clusterSize) * gridSize;
                int clusterY = rand.nextInt(clusterSize) * gridSize;

                // Ensure the bounds for random placement within the cluster are positive
                int maxOffsetX = Math.max(gridSize - width, 1); // Ensure at least 1
                int maxOffsetY = Math.max(gridSize - height, 1); // Ensure at least 1

                position = new Vector(clusterX + rand.nextInt(maxOffsetX), clusterY + rand.nextInt(maxOffsetY));
            } while (isOverlapping(position, elements, width, height) || isOnPath(position));
            elements.add(position);
        }
    }


    private boolean isOverlapping(Vector position, List<Vector> existingElements, int width, int height) {
        for (Vector other : existingElements) {
            if (Math.abs(position.x - other.x) < width && Math.abs(position.y - other.y) < height) {
                return true;
            }
        }
        return false;
    }

    private boolean isOnPath(Vector position) {
        for (Vector pathTile : path.getPathTiles()) {
            if (Math.abs(position.x - pathTile.x) < 50 && Math.abs(position.y - pathTile.y) < 50) {
                return true;
            }
        }
        return false;
    }
}
