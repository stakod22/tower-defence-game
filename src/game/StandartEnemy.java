package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StandartEnemy extends Enemy{

    public StandartEnemy(Vector location, List<PathSegment> segments) {
        super(location,5, segments, 2);
        super.setSize(30);
        super.setMoneyToGive(10);
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
