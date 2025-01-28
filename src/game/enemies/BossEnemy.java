package game.enemies;

import game.path.PathSegment;
import game.framework.Vector;

import java.awt.*;
import java.util.List;

public class BossEnemy extends Enemy{
    public BossEnemy(Vector location, List<PathSegment> segments) {
        new Enemy.Builder()
                .setLocation(location)
                .setHealth(100)
                .setSegments(segments)
                .setSpeedValue(1f)
                .setSize(40)
                .setMoneyToGive(46)
                .setEnemyType(EnemyType.BOSS)
                .finalizeBuild(this);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        int size = super.getSize(); // Balloon size determines its overall scale
        int centerX = (int)super.getLocation().x; // Balloon's center X
        int centerY = (int)super.getLocation().y; // Balloon's center Y

        // Generate vertices for the rounded balloon shape (e.g., a top ellipse with a taper)
        int numSides = 20; // Number of points for smooth roundness
        int[] xPoints = new int[numSides + 2]; // Extra points for the taper at the bottom
        int[] yPoints = new int[numSides + 2];

        // Upper part (round balloon)
        int balloonWidth = size+1;
        int balloonHeight = (int) (size * 1.5); // Slightly taller than wide
        for (int i = 0; i <= numSides; i++) {
            double angle = Math.PI * i / numSides; //
            xPoints[i] = centerX + (int) ((double) balloonWidth / 2 * Math.cos(angle));
            yPoints[i] = centerY - (int) ((double) balloonHeight / 2 * Math.sin(angle));
        }

        // Tapered bottom part
        xPoints[numSides + 1] = centerX;
        yPoints[numSides + 1] = centerY + balloonHeight / 3; // Taper point

        // Draw the balloon body
        g.setColor(Color.blue);
        g.fillPolygon(xPoints, yPoints, numSides + 2);

        // Draw the basket
        int basketWidth = size / 4;
        int basketHeight = size / 6;
        int basketX = centerX - basketWidth / 2;
        int basketY = centerY + balloonHeight / 3 + basketHeight / 2;
        g.setColor(Color.orange);
        g.fillRect(basketX, basketY, basketWidth, basketHeight);

        // Draw connecting lines between the balloon and the basket
        g.setColor(Color.black);
        g.drawLine(centerX - basketWidth / 2, basketY, centerX - balloonWidth / 4, centerY + balloonHeight / 3);
        g.drawLine(centerX + basketWidth / 2, basketY, centerX + balloonWidth / 4, centerY + balloonHeight / 3);

        // Optionally, draw an ID or decoration
        g.setColor(Color.white);
        g.drawString("" + getId(), centerX - 10, centerY);
        g.setColor(Color.black);
    }

}
