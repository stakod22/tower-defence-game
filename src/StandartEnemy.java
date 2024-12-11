import java.awt.*;

public class StandartEnemy extends Enemy{

    public StandartEnemy(int id,Vector location,int distanceTraveled) {


        super(id,location,distanceTraveled);
        Vector speed = new Vector(0,-10);
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
