package game.gui;

import game.framework.Drawable;
import game.framework.Vector;
import game.path.GamePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundWater implements Drawable {
    private List<Vector> corals = new ArrayList<>();
    private List<Vector> rocks = new ArrayList<>();
    private List<Vector> seagrass = new ArrayList<>();
    private List<Vector> fish = new ArrayList<>();
    private List<Vector> bubbles = new ArrayList<>();
    private List<Vector> variations = new ArrayList<>();
    private Random rand = new Random();
    private GamePath path;

    public BackgroundWater(GamePath path) {
        this.path = path;

        // Generate elements
        generateClusteredElements(corals, 30, 20, 20, 4); // Corals in 4x4 clusters
        generateClusteredElements(rocks, 20, 15, 15, 5); // Rocks in 5x5 clusters
        generateClusteredElements(seagrass, 50, 10, 10, 6); // Seagrass in 6x6 clusters
        generateRandomElements(fish, 20, 800, 800); // Fish scattered randomly
        generateRandomElements(bubbles, 40, 800, 800); // Bubbles scattered randomly

        for (int i = 0; i < 100; i++) {
            variations.add(new Vector(rand.nextFloat(800), rand.nextFloat(800))); // Slight darker patches for texture
        }
    }

    @Override
    public void update() {
        // Move fish and bubbles
        for (Vector fishPos : fish) {
            fishPos.x += rand.nextDouble() * 2 - 1; // Random horizontal movement
            fishPos.y += rand.nextDouble() * 2 - 1; // Random vertical movement

            // Wrap around screen
            if (fishPos.x < 0) fishPos.x = 800;
            if (fishPos.x > 800) fishPos.x = 0;
            if (fishPos.y < 0) fishPos.y = 800;
            if (fishPos.y > 800) fishPos.y = 0;
        }

        for (Vector bubble : bubbles) {
            bubble.y -= 1; // Bubbles float upwards
            if (bubble.y < 0) bubble.y = 800; // Reset bubble position
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw underwater background
        drawUnderwaterField(g);

        // Draw corals
        for (Vector coral : corals) {
            drawCoral(g, (int) coral.x, (int) coral.y);
        }

        // Draw rocks
        for (Vector rock : rocks) {
            drawRock(g, (int) rock.x, (int) rock.y);
        }

        // Draw seagrass
        for (Vector grass : seagrass) {
            drawSeagrass(g, (int) grass.x, (int) grass.y);
        }

        // Draw fish
        for (Vector fishPos : fish) {
            drawFish(g, (int) fishPos.x, (int) fishPos.y);
        }

        // Draw bubbles
        for (Vector bubble : bubbles) {
            drawBubble(g, (int) bubble.x, (int) bubble.y);
        }
    }

    private void drawUnderwaterField(Graphics2D g) {
        g.setColor(new Color(0, 105, 148)); // Ocean blue
        g.fillRect(0, 0, 800, 800);

        g.setColor(new Color(0, 75, 128));
        for(Vector v: variations){
            g.fillOval((int)v.x,(int)v.y, 5, 5); // Slight darker patches for texture
        }

    }

    private void drawCoral(Graphics2D g, int x, int y) {
        g.setColor(new Color(255, 99, 71)); // Coral red
        g.fillOval(x - 10, y - 10, 20, 20);
        g.setColor(new Color(255, 140, 105));
        g.drawLine(x, y, x + rand.nextInt(10) - 5, y + rand.nextInt(10) - 5);
    }

    private void drawRock(Graphics2D g, int x, int y) {
        g.setColor(new Color(128, 128, 128)); // Gray rocks
        g.fillOval(x - 8, y - 5, 16, 10);
    }

    private void drawSeagrass(Graphics2D g, int x, int y) {
        g.setColor(new Color(34, 139, 34)); // Green seagrass
        for (int i = 0; i < 3; i++) {
            g.drawLine(x, y, x + rand.nextInt(5) - 2, y - rand.nextInt(10));
        }
    }

    private void drawFish(Graphics2D g, int x, int y) {
        g.setColor(Color.YELLOW);
        g.fillOval(x - 5, y - 2, 10, 5);
        g.setColor(Color.ORANGE);
        g.fillPolygon(new int[]{x - 5, x - 7, x - 5}, new int[]{y - 2, y, y + 2}, 3); // Tail
    }

    private void drawBubble(Graphics2D g, int x, int y) {
        g.setColor(new Color(173, 216, 230, 150)); // Transparent light blue bubbles
        g.drawOval(x, y, 8, 8);
    }

    private void generateClusteredElements(List<Vector> elements, int count, int width, int height, int clusterSize) {
        int gridSize = 800 / clusterSize; // Divide the field into a grid
        for (int i = 0; i < count; i++) {
            Vector position;
            do {
                int clusterX = rand.nextInt(clusterSize) * gridSize;
                int clusterY = rand.nextInt(clusterSize) * gridSize;

                // Ensure the bounds for random placement within the cluster are positive
                int maxOffsetX = Math.max(gridSize - width, 1);
                int maxOffsetY = Math.max(gridSize - height, 1);

                position = new Vector(clusterX + rand.nextInt(maxOffsetX), clusterY + rand.nextInt(maxOffsetY));
            } while (isOverlapping(position, elements, width, height) || isOnPath(position));
            elements.add(position);
        }
    }

    private void generateRandomElements(List<Vector> elements, int count, int width, int height) {
        for (int i = 0; i < count; i++) {
            elements.add(new Vector(rand.nextInt(width), rand.nextInt(height)));
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
