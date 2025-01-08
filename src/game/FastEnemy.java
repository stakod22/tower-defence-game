package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class FastEnemy extends Enemy{

    public FastEnemy(Vector location, List<PathSegment> segments) {
        super(location,3, segments, 10);
        super.setSize(20);
        super.setMoneyToGive(15);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.blue);
        g.fillRect(super.getLocation().x-(super.getSize()/2),super.getLocation().y-(super.getSize()/2),super.getSize(),super.getSize());
        g.setColor(Color.black);
        g.drawString(""+getId(),super.getLocation().x,super.getLocation().y);
    }
}
