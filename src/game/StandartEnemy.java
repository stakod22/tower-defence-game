package game;

import java.awt.*;

public class StandartEnemy extends Enemy{

    public StandartEnemy(Vector location) {
        super(location,20);
        super.setSpeed(new Vector(0,1));
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.cyan);
        g.fillRect(super.getLocation().x,super.getLocation().y,40,40);
        g.setColor(Color.black);
        g.drawString(""+getId(),super.getLocation().x,super.getLocation().y);
    }
}
