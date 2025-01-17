package game.enemies;

import game.path.PathSegment;
import game.framework.Vector;

import java.awt.*;
import java.util.List;

public class FastEnemy extends Enemy{

    public FastEnemy(Vector location, List<PathSegment> segments) {
        super(location,3, segments, 5);
        super.setSize(20);
        super.setMoneyToGive(7);
        super.setColor(Color.blue);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        super.draw(g);
    }
}
