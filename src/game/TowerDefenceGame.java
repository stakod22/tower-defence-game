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
    private int health = 100;
    private int money = 20;

    public TowerDefenceGame(){
        addEnemy(new StandartEnemy(new Vector(250,-50), gamePath.getSegments()));
        addEnemy(new StandartEnemy(new Vector(250,-100), gamePath.getSegments()));
        addEnemy(new StandartEnemy(new Vector(250,-250), gamePath.getSegments()));

        addEnemy(new FastEnemy(new Vector(250,-100), gamePath.getSegments()));

        addTower(new StandardTower(new Vector(305,100),enemyList));


        //INIT Waves

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
        towerList.update(enemyList);
        projectileList.update(enemyList);
        enemyList.update();

        //Sorting everything for correct drawing and updating
        figures.clear();
        figures.add(gamePath);
        for (int i = 0; i < towerList.getTowerList().size(); i++) {
            figures.add(towerList.getTowerList().get(i));
        }
        for (int i = 0; i < projectileList.getProjectileList().size(); i++) {
            figures.add(projectileList.getProjectileList().get(i));
        }
        for (int i = 0; i < enemyList.getEnemyList().size(); i++) {
            figures.add(enemyList.getEnemyList().get(i));
        }

        // updating
        for(Drawable d : figures) {
            d.update();
        }
        health -= enemyList.doDamage();
    }

    public void draw(Graphics2D g){
        g.drawString("Health: "+health,750,20);


        for(Drawable d : figures) {
            d.draw(g);
        }
    }

    public GamePath getGamePath(){
        return (GamePath) figures.get(0);
    }

}
