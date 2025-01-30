package game;

import game.enemies.*;
import game.framework.*;
import game.gui.*;
import game.gui.Button;
import game.path.*;
import game.towers.*;
import game.wave.*;
import game.projectiles.*;

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
    private EnemyList enemyList = new EnemyList();
    private TowerList towerList = new TowerList();
    private final GamePath gamePath = new GamePath();
    private final ProjectileList projectileList = new ProjectileList();
    private final List<game.gui.Button> buttons = new ArrayList<>();
    private final WaveList waveList = new WaveList(30);
    private BackgroundWater backgroundWater;
    private BackgroundGrass backgroundGrass;
    private BackgroundVolcano backgroundVolcanic;
    public static int level = 0;
    private int health = 50;
    private int money = 20+(level-1)*5;
    private final GUI gui = new GUI();
    private final MenuGUI menuGUI = new MenuGUI();
    private boolean placingMode = false;
    private String currentTowerType = "default!!!----||null";
    private boolean paused = false;
    private int currentTowerId;
    private int maxId = 0;
    private int pressedKey;
    public static Screen screen;
    private final Button nextLevelButton = new Button(new Vector(400,600),200,100,"Next Level",Color.red,"nextLevel");
    private MainMenu mainMenu = new MainMenu();
    private int lastLevel;


    public TowerDefenceGame() {

        //addEnemy(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        //addTower(new FreezeTower(new Vector(CELL_WIDTH*6+25,CELL_HEIGHT*3+25)));
        //addEnemy(new RegenEnemy(new Vector(250, -50), gamePath.getSegments()));
        //addEnemy(new DistractEnemy(new Vector(250, -50),gamePath.getSegments()));
        //addEnemy(new DistractEnemy(new Vector(250, -100),gamePath.getSegments()));
        //addEnemy(new FastEnemy(new Vector(250, -300),gamePath.getSegments()));
        backgroundGrass = new BackgroundGrass(gamePath);
        backgroundWater =  new BackgroundWater(gamePath);
        backgroundVolcanic = new BackgroundVolcano();
        lastLevel = level;

        //INIT
        initButtons();
        screen = Screen.HOME;
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
        switch (screen){
            case HOME:
                if(level!= lastLevel){
                    lastLevel = level;
                    initLevel();
                    TowerDefenceGame.screen = Screen.GAME;
                }
                break;
            case SETTINGS:
                break;
            case GAME:
                figures.clear();
                if(level==1){
                    figures.add(backgroundGrass);
                }else if(level==2){
                    figures.add(backgroundWater);
                }else if(level==3){
                    figures.add(backgroundVolcanic);
                }
                figures.add(gamePath);
                figures.add(gui);
                figures.addAll(projectileList.getProjectileList());
                figures.addAll(towerList.getTowerList());
                figures.addAll(enemyList.getEnemyList());
                figures.add(menuGUI);
                figures.addAll(buttons);

                updateGUI();
                if(!paused){
                    projectileList.addAllProjectiles(towerList.update(enemyList));
                    projectileList.update(enemyList);
                    enemyList.update();

                    // updating
                    for (Drawable d : figures) {
                        d.update();
                    }

                    //Money and Health
                    health -= enemyList.doDamage();
                    money += enemyList.giveMoney();

                    if(health <= 0) {
                        screen = Screen.LOSE;
                    }else{
                        if (enemyList.getNumberOfEnemys() == 0) {

                            //sendNextWave
                            if (waveList.getNextWave() != null) {
                                addWaveToEnemies(waveList.getNextWave());
                                waveList.currentWaveDone();

                            }else {
                                screen = Screen.WIN;
                                if(level==3){
                                    nextLevelButton.setText("Back to menu");
                                }
                            }
                        }
                    }
                }
                break;
            case WIN:
                if(menuGUI.isMenuOpen()){
                    menuGUI.closeUpgradeMenu();
                }
                towerList.clear();
                enemyList.clearButNotNew();
                projectileList.clear();
                buttons.clear();
                initButtons();
                new Thread(() -> {
                    try {
                        Thread.sleep(1);  // Wait for 10 seconds
                        MusicPlayer.stopMusic();  // Stop the music
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            case LOSE:
                new Thread(() -> {
                    MusicPlayer.stopMusic();  // Stop the music
                }).start();
                break;
        }
    }

    public void draw(Graphics2D g) {
        switch (screen){
            case HOME:
                mainMenu.draw(g);
                break;
            case SETTINGS:
                break;
            case GAME:
                for (Drawable d : figures) {
                    if(d.getClass()!=MenuGUI.class&&d.getClass()!=Button.class){
                        d.draw(g);
                    }
                }
                //background.drawCloudShadow(g);
                menuGUI.draw(g);
                for(Button b:buttons){
                    b.draw(g);
                }
                menuGUI.drawPriceText(g);

                // Use a font that supports emoji
                g.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));

                // Draw health with a red heart
                g.setColor(Color.BLACK);
                g.drawString("Health: " + health + " ♥️", 825, 20);

                // Draw money with a gold coin substitute (circle)
                g.drawString("Money: " + money + " 🪙", 825, 45);

                g.drawString("Wave: " + waveList.getCurrentWave() + "/ "+waveList.getMaxWave()+" \uD83C\uDF0A", 825, 70);

                drawTowerPrice(g);
                break;
            case WIN:
                g.setFont(veryBigFont);
                g.setColor(Color.GREEN);
                g.drawString("You Win!", 150, 400);
                g.setColor(Color.black);
                g.setFont(standardFont);
                nextLevelButton.draw(g);
                break;
            case LOSE:
                g.setFont(veryBigFont);
                g.setColor(Color.red);
                g.drawString("You Lost!", 150, 400);
                g.setColor(Color.black);
                g.setFont(bigFont);
                g.drawString("Wave: " + waveList.getCurrentWave(), 400, 550);
                g.setFont(standardFont);
                break;
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

    public void handleMouseClickLeft(int x, int y) {
        switch (screen){
            case GAME:
                int gridX = x / CELL_WIDTH;
                int gridY = y / CELL_HEIGHT;

                String buttonName = "";
                for(Button b : buttons){
                    if(b.pressedButton(x,y)){
                        buttonName = b.getName();
                        if(buttonName.equals("TowerPlaced")&&!menuGUI.isMenuOpen()){
                            TowerButton towerButton = (TowerButton) b;
                            currentTowerId = towerButton.getId();
                        }
                    }
                }
                if(menuGUI.isMenuOpen()){
                    TowerButton tb = null;
                    Tower upgradeTower = null;
                    for(Button b : buttons) {
                        if (b.getName().equals("TowerPlaced")) {
                            tb = (TowerButton) b;
                            if(currentTowerId == tb.getId()){
                                upgradeTower = towerList.getTowerByCell((int) tb.getCorner().x / CELL_WIDTH, (int) tb.getCorner().y / CELL_HEIGHT);
                                break;
                            }
                        }
                    }
                    switch (buttonName){
                        case "ExitUpgradeMenu":
                            menuGUI.closeUpgradeMenu();
                            break;
                        case "Sell":
                            menuGUI.closeUpgradeMenu();
                            money += (int)((float)upgradeTower.getValue()*0.7f);
                            towerList.remove(upgradeTower);
                            buttons.remove(tb);
                            break;
                        case "FireRateUpgrade":
                            if(upgradeTower.getUpgradeFireRateCost()<=money && upgradeTower.getUpgradeFireRatePurchased() < 5){
                                money -= upgradeTower.getUpgradeFireRateCost();
                                upgradeTower.upgradeFireRate();
                            }

                            break;
                        case "RangeUpgrade":
                            if(upgradeTower.getUpgradeRangeCost()<=money && upgradeTower.getUpgradeRangePurchased() < 5){
                                money -= upgradeTower.getUpgradeRangeCost();
                                upgradeTower.upgradeRange();
                            }
                            break;
                        case "DamageUpgrade":
                            if(upgradeTower.getUpgradeDamageCost()<=money && upgradeTower.getUpgradeDamagePurchased() < 5){
                                money -= upgradeTower.getUpgradeDamageCost();
                                upgradeTower.upgradeDamage();
                            }

                            break;
                        case "PierceUpgrade":
                            if (upgradeTower.getUpgradePierceCost() <= money && upgradeTower.getUpgradePiercePurchased() < 5){
                                money -= upgradeTower.getUpgradePierceCost();
                                upgradeTower.upgradePierce();
                            }

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
                        case "BuyTower5":
                            if(currentTowerType.equals("ShotgunTower")&&placingMode){
                                placingMode = false;
                            }else{
                                placingMode = true;
                            }
                            currentTowerType = "ShotgunTower";
                            ShotgunTower shotgunTower = new ShotgunTower(new Vector(1,1));
                            gui.setTowerRange(shotgunTower.getRange());
                            break;
                        case "BuyTower6":
                            if(currentTowerType.equals("LaserTower")&&placingMode){
                                placingMode = false;
                            }else{
                                placingMode = true;
                            }
                            currentTowerType = "LaserTower";
                            LaserTower laserTower = new LaserTower(new Vector(1,1));
                            gui.setTowerRange(laserTower.getRange());
                            break;
                        case "Menu":
                            placingMode = false;
                            paused = !paused;
                            break;
                        case "TowerPlaced":
                            for(Button b : buttons){
                                if(b.getName().equals("TowerPlaced")){
                                    TowerButton tb  = (TowerButton) b;
                                    if(currentTowerId == tb.getId()){
                                        placingMode = false;
                                        if(tb.getCorner().x>400){
                                            menuGUI.openUpgradeMenu("LEFT",towerList.getTowerByCell((int)tb.getCorner().x/CELL_WIDTH,(int)tb.getCorner().y/CELL_HEIGHT),buttons,tb);
                                        }else{
                                            menuGUI.openUpgradeMenu("RIGHT",towerList.getTowerByCell((int)tb.getCorner().x/CELL_WIDTH,(int)tb.getCorner().y/CELL_HEIGHT),buttons,tb);
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
                break;

            case WIN:
                if(nextLevelButton.pressedButton(x, y)){
                    if(level==3){
                        screen = Screen.HOME;
                    }else{
                        level++;
                        initLevel();
                    }
                }
                break;
            case HOME:
                mainMenu.handleClick(x,y);
                break;
        }



    }

    public void handleMouseClickRight(){
        placingMode = false;
        if(menuGUI.isMenuOpen()){
            menuGUI.closeUpgradeMenu();

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
            int gridXT = (int)loc.x / CELL_WIDTH;
            int gridYT = (int)loc.y / CELL_HEIGHT;
            if(gridXT == gridX && gridYT == gridY){
                return false;
            }
        }
        return true;
    }

    private void placeTower(int gridX, int gridY) {
        Vector loc = new Vector(50 * gridX + 25, 50 * gridY + 25);
        switch (currentTowerType) {
            case "StandardTower":
                StandardTower newTower = new StandardTower(loc);
                if (money >= newTower.getCost()) {
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
                if (money >= newFastTower.getCost()) {
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
                if (money >= newSniperTower.getCost()) {
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
                if (money >= newFreezeTower.getCost()) {
                    money -= newFreezeTower.getCost();
                    addTower(newFreezeTower);
                    maxId++;
                    TowerButton tb = new TowerButton(newFreezeTower, maxId);
                    buttons.add(tb);
                }
                gui.setDeclinePlace(true);
                break;
            case "ShotgunTower":
                ShotgunTower newShotgunTower = new ShotgunTower(loc);
                if (money >= newShotgunTower.getCost()) {
                    money -= newShotgunTower.getCost();
                    addTower(newShotgunTower);
                    maxId++;
                    TowerButton tb = new TowerButton(newShotgunTower, maxId);
                    buttons.add(tb);
                }
                gui.setDeclinePlace(true);
                break;
            case "LaserTower":
                LaserTower newLaserTower = new LaserTower(loc);
                if (money >= newLaserTower.getCost()) {
                    money -= newLaserTower.getCost();
                    addTower(newLaserTower);
                    maxId++;
                    TowerButton tb = new TowerButton(newLaserTower, maxId);
                    buttons.add(tb);
                }
                gui.setDeclinePlace(true);
                break;
        }
    }

    public void handleKeyPress(int keyCode) {
        pressedKey = keyCode;
    }

    public void handleKeyRelease(int keyCode) {
        if(pressedKey==keyCode&& menuGUI.isMenuOpen()){
            TowerButton tb = null;
            Tower upgradeTower = null;
            for(Button b : buttons) {
                if (b.getName().equals("TowerPlaced")) {
                    tb = (TowerButton) b;
                    if(currentTowerId == tb.getId()){
                        upgradeTower = towerList.getTowerByCell((int) tb.getCorner().x / CELL_WIDTH, (int) tb.getCorner().y / CELL_HEIGHT);
                        break;
                    }
                }
            }
            switch (keyCode){
                case 49:
                    if(upgradeTower.getUpgradeFireRateCost()<=money && upgradeTower.getUpgradeFireRatePurchased() < 5){
                        money -= upgradeTower.getUpgradeFireRateCost();
                        upgradeTower.upgradeFireRate();
                    }

                    break;
                case 50:
                    if(upgradeTower.getUpgradeRangeCost()<=money && upgradeTower.getUpgradeRangePurchased() < 5){
                        money -= upgradeTower.getUpgradeRangeCost();
                        upgradeTower.upgradeRange();
                    }
                    break;
                case 51:
                    if(upgradeTower.getUpgradeDamageCost()<=money && upgradeTower.getUpgradeDamagePurchased() < 5){
                        money -= upgradeTower.getUpgradeDamageCost();
                        upgradeTower.upgradeDamage();
                    }
                    break;
                case 52:
                    if (upgradeTower.getUpgradePierceCost() <= money && upgradeTower.getUpgradePiercePurchased() < 5){
                        money -= upgradeTower.getUpgradePierceCost();
                        upgradeTower.upgradePierce();
                    }
                    break;
                default:
                    System.out.println("Invalid key"+keyCode);
                    break;
            }
        }
    }

    public void initLevel(){
        System.out.println("init level");
        lastLevel = level;
        money = 20+(level-1)*5;
        health = 100;
        screen = Screen.GAME;
        gamePath.updateLevel();
        waveList.init(gamePath);
        String filePath = "src\\res\\level"+level+".wav";
        new Thread(() -> {
            MusicPlayer m = new MusicPlayer();
            m.playWav(filePath, 1f); // Start the music
        }).start();
    }

    private void updateGUI(){
        gui.setPlacingmode(placingMode);
    }
    private void initButtons(){
        addButton(new game.gui.Button(new Vector(825,100),140,75,"Standard Tower", Color.blue,"BuyTower1"));
        addButton(new game.gui.Button(new Vector(825,200),140,75,"Rapid Fire Tower", Color.orange,"BuyTower2"));
        addButton(new game.gui.Button(new Vector(825,300),140,75,"Sniper Tower", new Color(64, 163, 79),"BuyTower3"));
        addButton(new game.gui.Button(new Vector(825,400),140, 75,"Freeze Tower", Color.cyan,"BuyTower4"));
        addButton(new game.gui.Button(new Vector(825,500),140, 75,"Shotgun Tower", new Color(189,166,133),"BuyTower5"));
        addButton(new game.gui.Button(new Vector(825,600),140, 75,"Laser Tower", new Color(253, 149,  0),"BuyTower6"));

        addButton(new game.gui.Button(new Vector(825,700),140,50,"Pause", new Color(76, 76, 76),"Menu"));
    }
    private void drawTowerPrice(Graphics2D g){
        g.drawString( new StandardTower(new Vector(0,0)).getCost()+ " 🪙", 875, 165);
        g.drawString( new RapidFireTower(new Vector(0,0)).getCost()+ " 🪙", 875, 265);
        g.drawString( new SniperTower(new Vector(0,0)).getCost()+ " 🪙", 875, 365);
        g.drawString( new FreezeTower(new Vector(0,0)).getCost()+ " 🪙", 875, 465);
        g.drawString( new ShotgunTower(new Vector(0,0)).getCost()+" 🪙",875, 565);
        g.drawString( new LaserTower(new Vector(0,0)).getCost()+" 🪙",875, 665);
    }
}

