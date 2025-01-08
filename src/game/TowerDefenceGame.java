package game;

import java.awt.*;
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
    private List<Button> buttons = new ArrayList<>();
    private WaveList waveList = new WaveList();
    private int health = 100;
    private int money = 20;
    private GUI gui = new GUI();

    public TowerDefenceGame() {
        /*addEnemy(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        addEnemy(new StandartEnemy(new Vector(250, -100), gamePath.getSegments()));
        addEnemy(new StandartEnemy(new Vector(250, -250), gamePath.getSegments()));

        addEnemy(new FastEnemy(new Vector(250, -100), gamePath.getSegments()));*/

        addTower(new StandardTower(new Vector(50*6+25,50*2+25), enemyList));

        addButton(new Button(new Vector(850,200),100,100,"Press", Color.blue,"Test1"));

        //INIT Waves
        INITWaves(gamePath);
    }

    public void addEnemy(Drawable enemy) {
        enemyList.addEnemy((Enemy) enemy);
    }

    public void addTower(Drawable tower) {
        towerList.addTower((Tower) tower);
    }

    public void addProjectile(Drawable e) {
        projectileList.addProjectile((Projectile) e);
    }

    public void addButton(Button b) {
        buttons.add(b);
    }

    public void update() {
        towerList.update(enemyList);
        projectileList.update(enemyList);
        enemyList.update();


        //Sorting everything for correct drawing and updating
        figures.clear();
        figures.add(gui);
        figures.add(gamePath);
        figures.addAll(towerList.getTowerList());
        figures.addAll(projectileList.getProjectileList());
        figures.addAll(enemyList.getEnemyList());
        figures.addAll(buttons);

        // updating
        for (Drawable d : figures) {
            d.update();
        }

        //Money and Health
        health -= enemyList.doDamage();
        money -= enemyList.giveMoney();

        if(health <= 0) {
            //you Lost
        }

        if (enemyList.getNumberOfEnemys() == 0) {

            //sendNextWave
            if (waveList.getNextWave() != null) {

                waveList.getNextWave().toString();
                addWaveToEnemies(waveList.getNextWave());
                waveList.currentWaveDone();
                }
            }
        }

    public void addWaveToEnemies(Wave w) {
        for (Enemy e : w.getEnemies()) {
            addEnemy(e);
        }
    }

    public void draw(Graphics2D g) {
        for (Drawable d : figures) {
            d.draw(g);
        }
        g.setColor(Color.red);
        g.drawString("Health: " + health, 550, 20);
        g.setColor(Color.yellow);
        g.drawString("Money: " + money, 700, 20);
        g.setColor(Color.black);
    }

    public GamePath getGamePath() {
        return (GamePath) figures.get(0);
    }


    private void INITWaves(GamePath p) {
        waveList.init(p);
    }

    public void handleMouseClick(int x, int y) {

        int gridX = x / CELL_WIDTH;
        int gridY = y / CELL_HEIGHT;

        String buttonName = "";
        for(Button b : buttons){
            if(b.pressedButton(x,y)){
                buttonName = b.getName();
            }
        }

        switch (buttonName) {
            case "Test1":
                System.out.println("niggas in paris");
                break;
            default:
                if(isCellEmpty(gridX, gridY)&&gridY<=15&&gridX<=15) {
                    placeTower(gridX, gridY);
                }
                break;
        }

    }



    private boolean isCellEmpty(int gridX, int gridY) {
        for(Tower t : towerList.getTowerList()){
            Vector loc = t.getLocation();
            int gridXT = loc.x / CELL_WIDTH;
            int gridYT = loc.y / CELL_HEIGHT;
            if(gridXT == gridX && gridYT == gridY){
                return false;
            }
        }
        return true;
    }

    private void placeTower(int gridX, int gridY) {
        Vector loc = new Vector(50*gridX+25,50*gridY+25);
        addTower(new StandardTower(loc,enemyList));
    }


}

