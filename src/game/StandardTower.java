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


    public Projectile shootProjectile(){
        if(super.willShoot()) {

            return new Projectile(super.getLocation(), super.getTarget(), 2, 10) {
                @Override
                public void doDamage(EnemyList enemies) {
                    List<Enemy> enemyList = enemies.getEnemyList();
                    enemyList.forEach((enemy) -> {
                        enemy.getHit(super.getDamage());
                    });
                    System.out.println("I will do damage");
                }
            };
        }
        return null;
    }

    public void drawRange(Graphics2D g){
        g.setColor(Color.GREEN);
        g.drawOval(getLocation().x-getRange(),getLocation().y-getRange(),getRange()*2,getRange()*2);
        g.setColor(Color.BLACK);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(getLocation().x-20,getLocation().y-20,40,40);
        g.drawString(getTarget()+ " ",20,20);
    }
}
