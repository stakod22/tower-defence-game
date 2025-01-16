package game.gui;

import game.TowerDefenceGame;
import game.framework.Drawable;
import game.framework.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Background implements Drawable {
    private List<Vector> flowers = new ArrayList<>();
    private List<Vector> trees = new ArrayList<>();
    private List<Vector> grassTextures = new ArrayList<>();
    private List<Vector> riverTiles = new ArrayList<>();

    public Background() {
        Random rand = new Random();

        // Define river tiles (one cell to the left)
        for (int y = 0; y < 16; y++) {
            riverTiles.add(new Vector(150, y * TowerDefenceGame.CELL_HEIGHT));
        }

        // Generate random flowers
        int randomFlowerNumber = rand.nextInt(20) + 20;
        for (int i = 0; i < randomFlowerNumber; i++) {
            int randomX;
            int randomY;
            do {
                randomX = rand.nextInt(16);
                randomY = rand.nextInt(16);
            } while (isOnRiver(randomX, randomY));

            Vector flower = new Vector(randomX * TowerDefenceGame.CELL_WIDTH + 25, randomY * TowerDefenceGame.CELL_HEIGHT + 25);
            flowers.add(flower);
        }

        // Generate random trees
        int randomTreeNumber = rand.nextInt(10) + 10;
        for (int i = 0; i < randomTreeNumber; i++) {
            int randomX;
            int randomY;
            do {
                randomX = rand.nextInt(16);
                randomY = rand.nextInt(16);
            } while (isOnRiver(randomX, randomY));

            trees.add(new Vector(randomX * TowerDefenceGame.CELL_WIDTH, randomY * TowerDefenceGame.CELL_HEIGHT));
        }

        // Generate random grass texture spots
        int grassTextureNumber = rand.nextInt(50) + 50;
        for (int i = 0; i < grassTextureNumber; i++) {
            int randomX = rand.nextInt(800);
            int randomY = rand.nextInt(800);
            grassTextures.add(new Vector(randomX, randomY));
        }
    }

    @Override
    public void update() {
        // No dynamic updates needed for the background currently
    }

    @Override
    public void draw(Graphics2D g) {
        // Draw grassy field with slight variations
        drawGrassyField(g);

        // Draw river
        drawRiver(g);

        // Draw trees
        for (Vector tree : trees) {
            drawTree(g, (int) tree.x, (int) tree.y);
        }

        // Draw flowers
        for (Vector flower : flowers) {
            drawFlower(g, (int) flower.x, (int) flower.y);
        }

        g.setColor(Color.BLACK);
    }

    private void drawGrassyField(Graphics2D g) {
        g.setColor(new Color(138, 255, 132));
        g.fillRect(0, 0, 800, 800);

        // Add texture to the grassy field
        g.setColor(new Color(123, 240, 115));
        for (Vector texture : grassTextures) {
            g.fillOval((int) texture.x, (int) texture.y, 5, 5);
        }
    }

    private void drawFlower(Graphics2D g2d, int x, int y) {
        // Set color for the flower petals
        g2d.setColor(Color.PINK);

        // Draw petals
        g2d.fillOval(x - 10, y - 10, 20, 20); // Petal circle

        // Set color for the flower center
        g2d.setColor(Color.YELLOW);
        g2d.fillOval(x - 5, y - 5, 10, 10); // Center circle
    }

    private void drawTree(Graphics2D g2d, int x, int y) {
        // Draw trunk
        g2d.setColor(new Color(139, 69, 19)); // Brown color
        g2d.fillRect(x - 5, y, 10, 30);

        // Draw leaves
        g2d.setColor(new Color(34, 139, 34)); // Green color
        g2d.fillOval(x - 15, y - 20, 30, 30);
    }

    private void drawRiver(Graphics2D g2d) {
        g2d.setColor(new Color(64, 164, 223)); // River blue color

        for (Vector tile : riverTiles) {
            g2d.fillRect((int) tile.x, (int) tile.y, TowerDefenceGame.CELL_WIDTH, TowerDefenceGame.CELL_HEIGHT);
        }
    }

    private boolean isOnRiver(int gridX, int gridY) {
        for (Vector riverTile : riverTiles) {
            if (riverTile.x / TowerDefenceGame.CELL_WIDTH == gridX && riverTile.y / TowerDefenceGame.CELL_HEIGHT == gridY) {
                return true;
            }
        }
        return false;
    }
}
