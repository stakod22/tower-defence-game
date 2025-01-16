package game.enemies;

import game.path.PathSegment;
import game.framework.Vector;

import java.awt.*;
import java.util.List;

public class TankEnemy extends Enemy{

    public TankEnemy(Vector location, List<PathSegment> segments) {
        super(location,10, segments, 1);
        super.setSize(40);
        super.setMoneyToGive(10);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        int size = super.getSize();
        int centerX = (int) super.getLocation().x;
        int centerY = (int) super.getLocation().y;

        // Generate the vertices for the octagon
        int[] xPoints = new int[8];
        int[] yPoints = new int[8];
        for (int i = 0; i < 8; i++) {
            double angle = Math.toRadians(45 * i);
            xPoints[i] = (int) (centerX + (size / 2) * Math.cos(angle));
            yPoints[i] = (int) (centerY + (size / 2) * Math.sin(angle));
        }
        g.setColor(Color.darkGray);
        g.fillPolygon(xPoints, yPoints, 8);
        g.setColor(Color.black);

        g.drawString("" + getId(), centerX, centerY);
    }

}
