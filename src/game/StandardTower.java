package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StandardTower extends Tower {

    public StandardTower(Vector location,EnemyList enemyList) {
        super(location,100,enemyList);
        setCost(15);
    }


    public Projectile shootProjectile(List<Vector> enemyLocation){
        return null;
    }

    public void drawRange(Graphics2D g){
        g.setColor(Color.GREEN);
        g.drawOval(getLocation().x-getRange()+20,getLocation().y-getRange()+20,getRange()*2,getRange()*2);
        g.setColor(Color.BLACK);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(getLocation().x,getLocation().y,40,40);
        drawRange(g);
        g.drawString(getTargetID()+ " ",20,20);
    }
}
