package game.path;

import game.TowerDefenceGame;
import game.framework.Vector;
import game.framework.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GamePath implements Drawable {
    private List<Vector> pathTiles = new ArrayList<>();
    private List<PathSegment> segments = new ArrayList<>();
    private List<List<Vector>> veins = new ArrayList<>();
    public static int pathSize = 25;
    private List<Vector> borderCells = new ArrayList<>();

    public GamePath() {
        updateLevel();
    }

    public void loadPath() {
        if (TowerDefenceGame.level == 1) {
            pathTiles.add(new Vector(250, 0));
            pathTiles.add(new Vector(250, 50));
            pathTiles.add(new Vector(250, 100));
            pathTiles.add(new Vector(250, 150));
            pathTiles.add(new Vector(250, 200));
            segments.add(new PathSegment(225, 3));

            pathTiles.add(new Vector(300, 200));
            pathTiles.add(new Vector(350, 200));
            pathTiles.add(new Vector(400, 200));
            pathTiles.add(new Vector(450, 200));
            pathTiles.add(new Vector(500, 200));
            pathTiles.add(new Vector(550, 200));
            pathTiles.add(new Vector(600, 200));
            segments.add(new PathSegment(350, 2));

            pathTiles.add(new Vector(600, 250));
            pathTiles.add(new Vector(600, 300));
            pathTiles.add(new Vector(600, 350));
            pathTiles.add(new Vector(600, 400));
            segments.add(new PathSegment(200, 3));

            pathTiles.add(new Vector(550, 400));
            pathTiles.add(new Vector(500, 400));
            pathTiles.add(new Vector(450, 400));
            pathTiles.add(new Vector(400, 400));
            pathTiles.add(new Vector(350, 400));
            pathTiles.add(new Vector(300, 400));
            pathTiles.add(new Vector(250, 400));
            pathTiles.add(new Vector(200, 400));
            pathTiles.add(new Vector(150, 400));
            pathTiles.add(new Vector(100, 400));
            segments.add(new PathSegment(500, 4));

            pathTiles.add(new Vector(100, 450));
            pathTiles.add(new Vector(100, 500));
            pathTiles.add(new Vector(100, 550));
            pathTiles.add(new Vector(100, 600));
            pathTiles.add(new Vector(100, 650));
            pathTiles.add(new Vector(100, 700));
            pathTiles.add(new Vector(100, 750));
            segments.add(new PathSegment(400, 3));

            segments.add(new PathSegment(100, 5));
        } else if (TowerDefenceGame.level == 2) {
            pathTiles.add(new Vector(250, 0));
            pathTiles.add(new Vector(250, 50));
            pathTiles.add(new Vector(250, 100));
            pathTiles.add(new Vector(250, 150));
            pathTiles.add(new Vector(250, 200));
            pathTiles.add(new Vector(250, 250));
            pathTiles.add(new Vector(250, 300));
            pathTiles.add(new Vector(250, 350));
            segments.add(new PathSegment(375, 3));

            pathTiles.add(new Vector(300, 350));
            pathTiles.add(new Vector(350, 350));
            pathTiles.add(new Vector(400, 350));
            pathTiles.add(new Vector(450, 350));
            pathTiles.add(new Vector(500, 350));
            segments.add(new PathSegment(250, 2));

            pathTiles.add(new Vector(500, 300));
            pathTiles.add(new Vector(500, 250));
            segments.add(new PathSegment(100, 1));

            pathTiles.add(new Vector(550, 250));
            pathTiles.add(new Vector(600, 250));
            segments.add(new PathSegment(100, 2));

            pathTiles.add(new Vector(600, 300));
            pathTiles.add(new Vector(600, 350));
            pathTiles.add(new Vector(600, 400));
            pathTiles.add(new Vector(600, 450));
            pathTiles.add(new Vector(600, 500));
            pathTiles.add(new Vector(600, 550));
            pathTiles.add(new Vector(600, 600));
            pathTiles.add(new Vector(600, 650));
            pathTiles.add(new Vector(600, 700));
            pathTiles.add(new Vector(600, 750));
            segments.add(new PathSegment(500, 3));

            segments.add(new PathSegment(100, 5));
        } else if (TowerDefenceGame.level == 3) {
            pathTiles.add(new Vector(250, 0));
            pathTiles.add(new Vector(250, 50));
            pathTiles.add(new Vector(250, 100));
            pathTiles.add(new Vector(250, 150));
            segments.add(new PathSegment(175, 3));

            pathTiles.add(new Vector(200, 150));
            pathTiles.add(new Vector(150, 150));
            segments.add(new PathSegment(100, 4));

            pathTiles.add(new Vector(150, 200));
            pathTiles.add(new Vector(150, 250));
            pathTiles.add(new Vector(150, 300));
            pathTiles.add(new Vector(150, 350));
            pathTiles.add(new Vector(150, 400));
            pathTiles.add(new Vector(150, 450));
            pathTiles.add(new Vector(150, 500));
            pathTiles.add(new Vector(150, 550));
            segments.add(new PathSegment(400, 3));

            pathTiles.add(new Vector(200, 550));
            pathTiles.add(new Vector(250, 550));
            pathTiles.add(new Vector(300, 550));
            pathTiles.add(new Vector(350, 550));
            pathTiles.add(new Vector(400, 550));
            pathTiles.add(new Vector(450, 550));
            pathTiles.add(new Vector(500, 550));
            segments.add(new PathSegment(350, 2));

            pathTiles.add(new Vector(500, 600));
            pathTiles.add(new Vector(500, 650));
            pathTiles.add(new Vector(500, 700));
            pathTiles.add(new Vector(500, 750));
            segments.add(new PathSegment(200, 3));

            segments.add(new PathSegment(100, 5));
        }
    }

    private void initializeVeins() {
        veins.clear();
        int margin = 5; // Margin from the border of the tile
        for (Vector v : pathTiles) {
            List<Vector> tileVeins = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                // Generate start points within the central area of the tile
                int startX = (int) (v.x + margin + Math.random() * (50 - 2 * margin));
                int startY = (int) (v.y + margin + Math.random() * (50 - 2 * margin));
                // Generate end points within a constrained range around the start points
                int endX = startX + (int) (Math.random() * 20 - 10); // 20-pixel range
                int endY = startY + (int) (Math.random() * 20 - 10);
                // Clamp end points to stay within the tile's central area
                endX = (int) Math.min(Math.max(endX, v.x + margin), v.x + 50 - margin);
                endY = (int) Math.min(Math.max(endY, v.y + margin), v.y + 50 - margin);

                tileVeins.add(new Vector(startX, startY));
                tileVeins.add(new Vector(endX, endY));
            }
            veins.add(tileVeins);
        }
    }


    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        if (TowerDefenceGame.level == 1) {
            for (Vector v : pathTiles) {
                int x = (int) v.x;
                int y = (int) v.y;
                g.setColor(new Color(1, 93, 149));
                g.fillRect(x, y, 50, 50);
                g.setColor(Color.BLACK);
            }
        } else if (TowerDefenceGame.level == 2) {
            for (Vector v : pathTiles) {
                int x = (int) v.x;
                int y = (int) v.y;
                g.setColor(new Color(203, 189, 147));
                g.fillRect(x, y, 50, 50);
                g.setColor(Color.BLACK);
            }
        } else if (TowerDefenceGame.level == 3) {
            for(Vector b: borderCells){
                g.setColor(new Color(214, 81, 10));
                g.fillRect((int)b.x,(int)b.y,50,50);
            }
            int index = 0;
            for (Vector v : pathTiles) {
                int x = (int) v.x;
                int y = (int) v.y;

                // Draw the path tile
                g.setColor(new Color(46, 41, 58));
                g.fillRect(x, y, 50, 50);

                // Draw veins
                g.setColor(new Color(101, 21, 172));
                List<Vector> tileVeins = veins.get(index++);
                for (int i = 0; i < tileVeins.size(); i += 2) {
                    Vector start = tileVeins.get(i);
                    Vector end = tileVeins.get(i + 1);
                    g.drawLine((int) start.x, (int) start.y, (int) end.x, (int) end.y);
                }
            }
        }

    }

    public boolean isOnPath(int gridX, int gridY) {
        for (Vector p : pathTiles) {
            if (p.x / TowerDefenceGame.CELL_WIDTH == gridX && p.y / TowerDefenceGame.CELL_HEIGHT == gridY) {
                return true;
            }
        }
        if(TowerDefenceGame.level == 3){
            for (Vector b : borderCells) {
                if (b.x / TowerDefenceGame.CELL_WIDTH == gridX && b.y / TowerDefenceGame.CELL_HEIGHT == gridY) {
                    return true;
                }
            }
        }
        return false;
    }

    public void updateLevel() {
        segments.clear();
        pathTiles.clear();
        veins.clear();
        loadPath();
        initializeVeins();
        setExtendedBorderCells();
    }

    public List<PathSegment> getSegments() {
        return segments;
    }

    public List<Vector> getPathTiles() {
        return pathTiles;
    }

    public void setExtendedBorderCells() {
        borderCells = new ArrayList<>();
        int cellWidth = TowerDefenceGame.CELL_WIDTH;
        int cellHeight = TowerDefenceGame.CELL_HEIGHT;

        // A set to track visited cells for uniqueness
        Set<Vector> visited = new HashSet<>();

        for (Vector pathTile : pathTiles) {
            // Add all adjacent cells (top, bottom, left, right)
            int x = (int) pathTile.x;
            int y = (int) pathTile.y;

            addIfNotPath(x - cellWidth, y, visited, borderCells); // Left
            addIfNotPath(x + cellWidth, y, visited, borderCells); // Right
            addIfNotPath(x, y - cellHeight, visited, borderCells); // Top
            addIfNotPath(x, y + cellHeight, visited, borderCells); // Bottom
        }

        // Expand further to include cells adjacent to border cells
        List<Vector> expandedCells = new ArrayList<>();
        for (Vector borderCell : borderCells) {
            int x = (int) borderCell.x;
            int y = (int) borderCell.y;

            addIfNotPath(x - cellWidth, y, visited, expandedCells); // Left
            addIfNotPath(x + cellWidth, y, visited, expandedCells); // Right
            addIfNotPath(x, y - cellHeight, visited, expandedCells); // Top
            addIfNotPath(x, y + cellHeight, visited, expandedCells); // Bottom
        }

        borderCells.addAll(expandedCells);
        Vector rememberedCell1 = null;
        Vector rememberedCell2 = null;
        for(Vector b: borderCells){
            if(b.x == 200&&b.y==100){
                rememberedCell1 = b;
            }
            if(b.x == 450&&b.y==600){
                rememberedCell2 = b;
            }
        }
        if(rememberedCell1!=null){
            borderCells.remove(rememberedCell1);
        }
        if(rememberedCell2!=null){
            borderCells.remove(rememberedCell2);
        }
    }

    private void addIfNotPath(int x, int y, Set<Vector> visited, List<Vector> cells) {
        Vector cell = new Vector(x, y);
        if (!visited.contains(cell) && !isOnPath(x / TowerDefenceGame.CELL_WIDTH, y / TowerDefenceGame.CELL_HEIGHT)) {
            visited.add(cell);
            cells.add(cell);
        }
    }


}
