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

    public TowerDefenceGame(){
        figures.add(new GamePath());
        addEnemy(new StandartEnemy(figures.size(),new Vector(255,10)));
        addEnemy(new StandartEnemy(figures.size(),new Vector(255,-100)));
        addEnemy(new StandartEnemy(figures.size(),new Vector(255,-250)));
        addTower(new StandardTower(figures.size(),new Vector(305,100),enemyList));
    }

    public void addEnemy(Drawable enemy){
        figures.add(enemy);
        enemyList.addEnemy((Enemy) enemy);
    }
    public void addTower(Drawable tower){
        figures.add(tower);
        towerList.addTower((Tower) tower);
    }

    public void update(){
        for(Drawable d : figures) {
            d.update();
        }
        towerList.updateTargeting(enemyList);
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
