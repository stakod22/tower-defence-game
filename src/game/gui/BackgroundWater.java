package game.gui;

import game.framework.Drawable;
import game.framework.Vector;
import game.path.GamePath;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundWater implements Drawable {
    private List<Island> islands = new ArrayList<>();
    private List<Vector> waves = new ArrayList<>();
    private List<Vector> lilyPads = new ArrayList<>();
    private Random rand = new Random();
    private GamePath path; // Reference to the GamePath object

    public BackgroundWater(GamePath path) {
        this.path = path;

        // Generate random islands (considering the path for collision)
        generateIslands(10);

        // Generate random lily pads
        generateElements(lilyPads, 50, 20, 20);

        // Generate random waves
        for (int i = 0; i < 150; i++) {
            waves.add(new Vector(rand.nextInt(800), rand.nextInt(800)));
        }
    }

    @Override
    public void update() {
        // Move waves slowly for a dynamic effect
        for (Vector wave : waves) {
            wave.y += 0.5;
            if (wave.y > 800) {
                wave.y = 0; // Reset wave position
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw the water surface
        drawWaterSurface(g);

        // Draw islands
        for (Island island : islands) {
            drawIsland(g, island);
        }

        // Draw lily pads
        for (Vector lilyPad : lilyPads) {
            drawLilyPad(g, (int) lilyPad.x, (int) lilyPad.y);
        }

        // Draw waves
        for (Vector wave : waves) {
            drawWave(g, (int) wave.x, (int) wave.y);
        }
    }

    private void drawWaterSurface(Graphics2D g) {
        // Base color for water
        g.setColor(new Color(64, 164, 223)); // Light blue
        g.fillRect(0, 0, 800, 800);

        // Subtle variation in water color for texture
        g.setColor(new Color(52, 152, 219));
        for (int i = 0; i < 500; i++) {
            int x = rand.nextInt(800);
            int y = rand.nextInt(800);
            g.fillOval(x, y, 5, 5); // Small circles for water texture
        }
    }

    private void drawIsland(Graphics2D g, Island island) {
        // Draw island base
        g.setColor(new Color(194, 178, 128)); // Sandy color
        g.fillOval(island.x - island.width / 2, island.y - island.height / 2, island.width, island.height);

        // Add some green vegetation on the island
        g.setColor(new Color(34, 139, 34)); // Green color
        g.fillOval(island.x - island.width / 4, island.y - island.height / 4, island.width / 2, island.height / 2);

        // Add some irregular shapes to the island (optional)
        g.setColor(new Color(194, 178, 128)); // Sandy color
        for (Vector extension : island.extensions) {
            g.fillOval((int) (island.x + extension.x), (int) (island.y + extension.y), 20, 20);
        }
    }

    private void drawLilyPad(Graphics2D g, int x, int y) {
        // Draw lily pad base
        g.setColor(new Color(0, 100, 0)); // Dark green
        g.fillOval(x - 10, y - 5, 20, 10); // Oval-shaped lily pad

        // Draw a small flower on the lily pad
        g.setColor(Color.PINK);
        g.fillOval(x - 3, y - 3, 6, 6); // Flower in the center of the pad
    }

    private void drawWave(Graphics2D g, int x, int y) {
        // Draw waves as small semi-transparent arcs
        g.setColor(new Color(255, 255, 255, 100)); // Light white with transparency
        g.drawArc(x, y, 20, 10, 0, 180); // Wave arc
    }

    private void generateIslands(int count) {
        for (int i = 0; i < count; i++) {
            Island island;
            do {
                // Generate random island position and size
                int x = rand.nextInt(800);
                int y = rand.nextInt(800);
                int width = rand.nextInt(50) + 50; // Island width (50-100)
                int height = rand.nextInt(50) + 50; // Island height (50-100)

                // Generate random extensions for irregular shapes
                List<Vector> extensions = new ArrayList<>();
                int extensionCount = rand.nextInt(4); // 0-3 additional extensions
                for (int j = 0; j < extensionCount; j++) {
                    extensions.add(new Vector(rand.nextInt(40) - 20, rand.nextInt(40) - 20));
                }

                island = new Island(x, y, width, height, extensions);

            } while (isOverlappingWithPath(island) || isOverlappingWithOtherIslands(island));
            islands.add(island);
        }
    }

    private boolean isOverlappingWithPath(Island island) {
        for (Vector pathTile : path.getPathTiles()) {
            Rectangle pathRect = new Rectangle((int) pathTile.x, (int) pathTile.y, 50, 50);
            Rectangle islandRect = new Rectangle(island.x - island.width / 2, island.y - island.height / 2, island.width, island.height);
            if (pathRect.intersects(islandRect)) {
                return true; // Overlaps with the path
            }
        }
        return false;
    }

    private boolean isOverlappingWithOtherIslands(Island newIsland) {
        for (Island island : islands) {
            Rectangle existingIslandRect = new Rectangle(island.x - island.width / 2, island.y - island.height / 2, island.width, island.height);
            Rectangle newIslandRect = new Rectangle(newIsland.x - newIsland.width / 2, newIsland.y - newIsland.height / 2, newIsland.width, newIsland.height);
            if (existingIslandRect.intersects(newIslandRect)) {
                return true; // Overlaps with another island
            }
        }
        return false;
    }

    private void generateElements(List<Vector> elements, int count, int width, int height) {
        for (int i = 0; i < count; i++) {
            Vector position;
            do {
                position = new Vector(rand.nextInt(800 - width), rand.nextInt(800 - height));
            } while (isOnPath((int) position.x, (int) position.y));
            elements.add(position);
        }
    }

    private boolean isOnPath(int x, int y) {
        return path.isOnPath(x / 50, y / 50);
    }

    // Inner class for Island
    private static class Island {
        int x, y, width, height;
        List<Vector> extensions;

        public Island(int x, int y, int width, int height, List<Vector> extensions) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.extensions = extensions;
        }
    }
}
