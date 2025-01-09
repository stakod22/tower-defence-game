package game.towers;

import game.enemies.Enemy;
import game.enemies.EnemyList;
import game.framework.Vector;
import game.projectiles.Projectile;

import java.awt.*;
import java.util.List;

public class SniperTower extends Tower {
    public SniperTower(Vector location, EnemyList enemyList) {
        super(location,400,enemyList);
        setCost(50);
        setFirerate(250);
    }


    public Projectile shootProjectile(){
        if(super.willShoot()) {
            Vector loc = new Vector(super.getLocation().x,super.getLocation().y);
            Vector target = new Vector(super.getTarget().x,super.getTarget().y);
            return new Projectile(loc, target, 5, 10,2,50) {
                @Override
                public void doDamage(EnemyList enemies) {
                    List<Enemy> enemyList = enemies.getEnemyList();
                    enemyList.forEach((enemy) -> {
                        enemy.getHit(super.getDamage());
                        //super.hitOnce();
                    });
                }
            };
        }
        return null;
    }

    public void drawRange(Graphics2D g){
        g.drawOval(getLocation().x-getRange(),getLocation().y-getRange(),getRange()*2,getRange()*2);
        g.setColor(Color.BLACK);
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.green);
        g.fillRect(getLocation().x-20,getLocation().y-20,40,40);
        drawRange(g);
        //g.drawString(getTarget()+ " ",20,20);
    }
}
