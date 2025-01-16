package game.enemies;

import game.path.PathSegment;
import game.framework.Vector;

import java.awt.*;
import java.util.List;

public class StandartEnemy extends Enemy{

    public StandartEnemy(Vector location, List<PathSegment> segments) {
        super(location,5, segments, 1);
        super.setSize(30);
        super.setMoneyToGive(5);
        super.setColor(Color.cyan);
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
