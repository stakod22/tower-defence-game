package game;

import game.enemies.Enemy;
import game.enemies.EnemyList;
import game.enemies.StandartEnemy;
import game.framework.Drawable;
import game.framework.Vector;
import game.gui.Button;
import game.gui.GUI;
import game.path.GamePath;
import game.projectiles.ProjectileList;
import game.towers.RapidFireTower;
import game.towers.StandardTower;
import game.towers.Tower;
import game.towers.TowerList;
import game.wave.Wave;
import game.wave.WaveList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TowerDefenceGame {
    public static final int CELL_WIDTH = 50;
    public static final int CELL_HEIGHT = 50;

    private final Font veryBigFont = new Font("Arial", Font.BOLD, 150);
    private final Font bigFont = new Font("Arial", Font.PLAIN, 50);
    private final Font standardFont = new Font("Arial", Font.PLAIN, 12);
    private final List<Drawable> figures = new ArrayList<>();
    private final EnemyList enemyList = new EnemyList();
    private final TowerList towerList = new TowerList();
    private final GamePath gamePath = new GamePath();
    private final ProjectileList projectileList = new ProjectileList();
    private final List<game.gui.Button> buttons = new ArrayList<>();
    private final WaveList waveList = new WaveList();
    private int health = 50;
    private int money = 30;
    private final GUI gui = new GUI();
    private boolean placingMode = false;
    private boolean lost = false;
    private boolean win = false;
    private String currentTowerType = "default!!!----||null";
    private boolean paused = false;

    public TowerDefenceGame() {
        addEnemy(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));

        //addTower(new StandardTower(new Vector(50*6+25,50*2+25), enemyList));

        addButton(new game.gui.Button(new Vector(825,75),140,75,"Standard Tower", Color.blue,"BuyTower1"));
        addButton(new game.gui.Button(new Vector(825,175),140,75,"Rapid Fire Tower", Color.orange,"BuyTower2"));
        addButton(new game.gui.Button(new Vector(825,700),140,50,"Pause", Color.cyan,"Menu"));

        //INIT Waves
        initWaves(gamePath);
    }

    public void addEnemy(Drawable enemy) {
        enemyList.addEnemy((Enemy) enemy);
    }

    public void addTower(Drawable tower) {
        towerList.addTower((Tower) tower);
    }

    public void addButton(game.gui.Button b) {
        buttons.add(b);
    }

    public void update() {
        if(!win){
            figures.clear();
            figures.add(gui);
            figures.add(gamePath);
            figures.addAll(towerList.getTowerList());
            figures.addAll(projectileList.getProjectileList());
            figures.addAll(enemyList.getEnemyList());
            figures.addAll(buttons);

            if (!lost){
                if(!paused){
                    projectileList.addAllProjectiles(towerList.update(enemyList));
                    projectileList.update(enemyList);
                    enemyList.update();
                    updateGUI();

                    // updating
                    for (Drawable d : figures) {
                        d.update();
                    }

                    //Money and Health
                    health -= enemyList.doDamage();
                    money += enemyList.giveMoney();

                    if(health <= 0) {
                        lost = true;
                    }else{
                        if (enemyList.getNumberOfEnemys() == 0) {

                            //sendNextWave
                            if (waveList.getNextWave() != null) {
                                addWaveToEnemies(waveList.getNextWave());
                                waveList.currentWaveDone();
                            }else {
                                win = true;
                            }
                        }
                    }
                }
            }
        }
    }

    public void draw(Graphics2D g) {
        if (win){
            g.setFont(veryBigFont);
            g.setColor(Color.GREEN);
            g.drawString("You Win!",150,400);
            g.setColor(Color.black);
            g.setFont(standardFont);
        }else {
            if (!lost){
                for (Drawable d : figures) {
                    d.draw(g);
                }
                g.drawString("Health:\n\t" + health, 550, 20);
                g.drawString("Money:\n\t" + money, 700, 20);

            }else{
                g.setFont(veryBigFont);
                g.setColor(Color.red);
                g.drawString("You Lost!",150,400);
                g.setColor(Color.black);
                g.setFont(bigFont);
                g.drawString("Wave: "+waveList.getCurrentWave()+1,400,550);
                g.setFont(standardFont);
            }
        }


    }


    private void initWaves(GamePath p) {
        waveList.init(p);
    }
    public void addWaveToEnemies(Wave w) {
        for (Enemy e : w.getEnemies()) {
            addEnemy(e);
        }
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
            case "BuyTower1":
                if(currentTowerType.equals("StandardTower")||currentTowerType.equals("default!!!----||null")) {
                    placingMode = !placingMode;
                }
                currentTowerType = "StandardTower";
                break;
            case "BuyTower2":
                if(currentTowerType.equals("FastTower")||currentTowerType.equals("default!!!----||null")) {
                    placingMode = !placingMode;
                }
                currentTowerType = "FastTower";
                break;
            case "Menu":
                paused = !paused;
                break;
            default:
                if(isCellEmpty(gridX, gridY)&&gridY<=15&&gridX<=15&& placingMode) {
                    placeTower(gridX, gridY);
                }
                break;
        }

    }

    public void handleMouseDragged(int x, int y) {
        int gridX = x / CELL_WIDTH;
        int gridY = y / CELL_HEIGHT;
        gui.setMouse(new Vector(gridX,gridY));
        gui.setCellEmpty(isCellEmpty(gridX,gridY));
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
        switch(currentTowerType){
            case "StandardTower":
                StandardTower newTower = new StandardTower(loc,enemyList);
                if(money>= newTower.getCost()){
                    money -= newTower.getCost();
                    addTower(newTower);
                }
                gui.setDeclinePlace(true);
                break;
            case "FastTower":
                RapidFireTower newFastTower = new RapidFireTower(loc,enemyList);
                if(money>= newFastTower.getCost()){
                    money -= newFastTower.getCost();
                    addTower(newFastTower);
                }
                gui.setDeclinePlace(true);
                break;
        }

    }

    private void updateGUI(){
        gui.setPlacingmode(placingMode);
    }
}

