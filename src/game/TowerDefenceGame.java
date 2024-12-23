package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class TowerDefenceGame {
    public static final int CELL_WIDTH = 50;
    public static final int CELL_HEIGHT = 50;

    private List<Drawable> figures = new ArrayList<>();
    private EnemyList enemyList = new EnemyList();
    private TowerList towerList = new TowerList();
    private GamePath gamePath = new GamePath();
    private ProjectileList projectileList = new ProjectileList();
    private WaveList waveList = new WaveList();

    public TowerDefenceGame(){
        addEnemy(new StandartEnemy(enemyList.getEnemyList().size(),new Vector(255,10)));
        addEnemy(new StandartEnemy(enemyList.getEnemyList().size(),new Vector(255,-100)));
        addEnemy(new StandartEnemy(enemyList.getEnemyList().size(),new Vector(255,-250)));
        addTower(new StandardTower(towerList.getTowerList().size(),new Vector(305,100),enemyList));
    }

    public void addEnemy(Drawable enemy){
        enemyList.addEnemy((Enemy) enemy);
    }
    public void addTower(Drawable tower){
        towerList.addTower((Tower) tower);
    }
    public void addProjectile(Drawable e){
        projectileList.addProjectile((Projectile) e);
    }

    public void update(){
        towerList.updateTargeting(enemyList);
        figures.clear();
        figures.add(gamePath);
        for (int i = 0; i < towerList.getTowerList().size(); i++) {
            figures.add(towerList.getTowerList().get(i));
        }
        for (int i = 0; i < enemyList.getEnemyList().size(); i++) {
            figures.add(enemyList.getEnemyList().get(i));
        }
        for (int i = 0; i < projectileList.getProjectileList().size(); i++) {
            figures.add(projectileList.getProjectileList().get(i));
        }

        for(Drawable d : figures) {
            d.update();
        }

    }

    public void draw(Graphics2D g){
        for(Drawable d : figures) {
            d.draw(g);
        }
    }

    public GamePath getGamePath(){
        return (GamePath) figures.get(0);
    }

}
