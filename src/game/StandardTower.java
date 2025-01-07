package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StandardTower extends Tower {

    public StandardTower(Vector location,EnemyList enemyList) {
        super(location,100,enemyList);
        setCost(15);
        setFirerate(1);
    }


    public Projectile shootProjectile(EnemyList enemies){
        if(super.willShoot()) {
            return new Projectile(1, super.getLocation(), super.getTarget(), 1, 10) {
                @Override
                public void doDamage(EnemyList enemies) {
                    List<Enemy> enemyList = enemies.getEnemyList();
                    enemyList.forEach((enemy) -> {
                        enemy.getHit(super.getDamage());
                    });
                }
            };
        }
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
        g.drawString(getTarget()+ " ",20,20);
    }
}
