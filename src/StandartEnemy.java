import java.awt.*;

public class StandartEnemy extends Enemy{

    public StandartEnemy(int id,Vector location,int distanceTraveled) {
        Vector speed = new Vector(0,-10);
        super(id,location,distanceTraveled);
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
