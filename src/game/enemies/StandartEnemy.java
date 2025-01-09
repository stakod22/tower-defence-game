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
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.cyan);
        g.fillRect(super.getLocation().x-(super.getSize()/2),super.getLocation().y-(super.getSize()/2),super.getSize(),super.getSize());
        g.setColor(Color.black);
        g.drawString(""+getId(),super.getLocation().x,super.getLocation().y);
    }
}
