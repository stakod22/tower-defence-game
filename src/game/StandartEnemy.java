package game;

import java.awt.*;

public class StandartEnemy extends Enemy{

    public StandartEnemy(int id,Vector location) {
        super(id,location,20);
        super.setSpeed(new Vector(0,1));
    }

    @Override
    public void update() {
        super.getLocation().add(super.getSpeed());
    }

    @Override
    public void draw(Graphics2D g) {
        g.drawRect(super.getLocation().x,super.getLocation().y,40,40);
    }
}
