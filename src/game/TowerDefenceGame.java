package game;

import game.enemies.*;
import game.framework.*;
import game.gui.*;
import game.gui.Button;
import game.path.*;
import game.towers.*;
import game.wave.*;
import game.projectiles.*;

import javax.swing.*;
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
    private int health = 150;
    private int money = 30;
    private final GUI gui = new GUI();
    private final MenuGUI menuGUI = new MenuGUI();
    private boolean placingMode = false;
    private boolean lost = false;
    private boolean win = false;
    private String currentTowerType = "default!!!----||null";
    private boolean paused = false;
    private int currentTowerId;
    private int maxId = 0;

    public TowerDefenceGame() {

        //addEnemy(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));




        //INIT
        initButtons();
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
            figures.add(menuGUI);
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
        if (win) {
            g.setFont(veryBigFont);
            g.setColor(Color.GREEN);
            g.drawString("You Win!", 150, 400);
            g.setColor(Color.black);
            g.setFont(standardFont);
        } else {
            if (!lost) {
                for (Drawable d : figures) {
                    d.draw(g);
                }

                // Use a font that supports emoji
                g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));

                // Draw health with a red heart
                g.setColor(Color.BLACK);
                g.drawString("Health: " + health + " ♥️", 825, 20);

                // Draw money with a gold coin substitute (circle)
                g.drawString("Money: " + money + " 🪙", 825, 50);

            } else {
                g.setFont(veryBigFont);
                g.setColor(Color.red);
                g.drawString("You Lost!", 150, 400);
                g.setColor(Color.black);
                g.setFont(bigFont);
                g.drawString("Wave: " + waveList.getCurrentWave(), 400, 550);
                g.setFont(standardFont);
            }
        }
    }




    private void initWaves(GamePath p) {
        waveList.init(p);
        //waveList.debug(p);
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
                if(buttonName.equals("TowerPlaced")){
                    TowerButton towerButton = (TowerButton) b;
                    currentTowerId = towerButton.getId();
                }
            }
        }
        if(menuGUI.isMenuOpen()){
            switch (buttonName){
                case "ExitUpgradeMenu":
                    menuGUI.closeUpgradeMenu();
                    break;
            }
        }else{
            switch (buttonName) {
                case "BuyTower1":
                    if(currentTowerType.equals("StandardTower")&&placingMode){
                        placingMode = false;
                    }else{
                        placingMode = true;
                    }
                    currentTowerType = "StandardTower";
                    StandardTower standardTowerTower = new StandardTower(new Vector(1,1));
                    gui.setTowerRange(standardTowerTower.getRange());
                    break;
                case "BuyTower2":
                    if(currentTowerType.equals("FastTower")&&placingMode){
                        placingMode = false;
                    }else{
                        placingMode = true;
                    }
                    currentTowerType = "FastTower";
                    RapidFireTower fastTower = new RapidFireTower(new Vector(1,1));
                    gui.setTowerRange(fastTower.getRange());
                    break;
                case "BuyTower3":
                    if(currentTowerType.equals("SniperTower")&&placingMode){
                        placingMode = false;
                    }else{
                        placingMode = true;
                    }
                    currentTowerType = "SniperTower";
                    SniperTower sniperTower = new SniperTower(new Vector(1,1));
                    gui.setTowerRange(sniperTower.getRange());
                    break;

                case "BuyTower4":
                    if(currentTowerType.equals("FreezeTower")&&placingMode){
                        placingMode = false;
                    }else{
                        placingMode = true;
                    }
                    currentTowerType = "FreezeTower";
                    FreezeTower freezeTower = new FreezeTower(new Vector(1,1));
                    gui.setTowerRange(freezeTower.getRange());
                    break;
                case "Menu":
                    paused = !paused;
                    break;
                case "TowerPlaced":
                    for(Button b : buttons){
                        if(b.getName().equals("TowerPlaced")){
                            TowerButton tb  = (TowerButton) b;
                            if(currentTowerId == tb.getId()){
                                placingMode = false;
                                if(tb.getCorner().x>400){
                                    menuGUI.openUpgradeMenu("LEFT",towerList.getTowerByCell(tb.getCorner().x/CELL_WIDTH,tb.getCorner().y/CELL_HEIGHT),buttons,tb);
                                }else{
                                    menuGUI.openUpgradeMenu("RIGHT",towerList.getTowerByCell(tb.getCorner().x/CELL_WIDTH,tb.getCorner().y/CELL_HEIGHT),buttons,tb);
                                }
                                break;
                            }
                        }
                    }
                    break;

                default:
                    if(isCellEmpty(gridX, gridY)&&gridY<=15&&gridX<=15&& placingMode) {
                        if(!gamePath.isOnPath(gridX, gridY)){
                            placeTower(gridX, gridY);
                        }
                    }
                    break;
            }
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
                StandardTower newTower = new StandardTower(loc);
                if(money>= newTower.getCost()){
                    money -= newTower.getCost();
                    addTower(newTower);
                    maxId++;
                    TowerButton tb = new TowerButton(newTower, maxId);
                    buttons.add(tb);
                }
                gui.setDeclinePlace(true);
                break;
            case "FastTower":
                RapidFireTower newFastTower = new RapidFireTower(loc);
                if(money>= newFastTower.getCost()){
                    money -= newFastTower.getCost();
                    addTower(newFastTower);
                    maxId++;
                    TowerButton tb = new TowerButton(newFastTower, maxId);
                    buttons.add(tb);
                }
                gui.setDeclinePlace(true);
                break;
            case "SniperTower":
                SniperTower newSniperTower = new SniperTower(loc);
                if(money>= newSniperTower.getCost()){
                    money -= newSniperTower.getCost();
                    addTower(newSniperTower);
                    maxId++;
                    TowerButton tb = new TowerButton(newSniperTower, maxId);
                    buttons.add(tb);
                }
                gui.setDeclinePlace(true);
                break;
            case "FreezeTower":
                FreezeTower newFreezeTower = new FreezeTower(loc);
                if(money>= newFreezeTower.getCost()){
                    money -= newFreezeTower.getCost();
                    addTower(newFreezeTower);
                    maxId++;
                    TowerButton tb = new TowerButton(newFreezeTower, maxId);
                    buttons.add(tb);
                }
                gui.setDeclinePlace(true);
                break;
        }

    }

    private void updateGUI(){
        gui.setPlacingmode(placingMode);
    }
    private void initButtons(){
        addButton(new game.gui.Button(new Vector(825,75),140,75,"Standard Tower", Color.blue,"BuyTower1"));
        addButton(new game.gui.Button(new Vector(825,175),140,75,"Rapid Fire Tower", Color.orange,"BuyTower2"));
        addButton(new game.gui.Button(new Vector(825,275),140,75,"Sniper Tower", new Color(64, 163, 79),"BuyTower3"));
        addButton(new game.gui.Button(new Vector(825,375),140, 75,"Freeze Tower", Color.cyan,"BuyTower4"));

        addButton(new game.gui.Button(new Vector(825,700),140,50,"Pause", new Color(76, 76, 76),"Menu"));
    }
}

