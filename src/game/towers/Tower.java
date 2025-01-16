package game.towers;

import game.framework.Drawable;
import game.projectiles.Projectile;
import game.framework.Vector;
import game.enemies.Enemy;
import game.enemies.EnemyList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class Tower implements Drawable {
    private int id;
    private Vector location;
    private Vector target = new Vector(0, 0);
    private int range;
    private int cost;
    private int firerate;
    private boolean willShoot = false;
    private int shootableFrameCount = 0;
    private int pierce;
    private int damage;
    private Color towerColor;
    private int upgradePierceCost = 10;
    private int upgradePiercePurchased = 0;
    private int upgradeFireRateCost = 10;
    private int upgradeFireRatePurchased = 0;
    private int upgradeDamageCost = 10;
    private int upgradeDamagePurchased = 0;
    private int upgradeRangeCost = 10;
    private int upgradeRangePurchased = 0;



    private TowerType towerType;


    public Tower(Vector location,int range) {
        this.location = location;
        this.range = range;
    }

    abstract Projectile shootProjectile();


    public void seeEnemies(EnemyList enemies){
        List<Enemy> seenEnemies;
        seenEnemies = enemies.inRange(range, location).getSortedEnemyList("distance");
        if (!seenEnemies.isEmpty()){
            setTarget(seenEnemies.get(0).getLocation());
            willShoot = true;
        }else{
            setTarget(null);
            willShoot = false;
        }

    }

    public List<Projectile> update(EnemyList enemies){
        seeEnemies(enemies);
        List<Projectile> projectiles = new ArrayList<>();
        if(willShoot){
            if (shootableFrameCount >= firerate){
                projectiles.add(shootProjectile());
                shootableFrameCount = 0;
            }
        }
        shootableFrameCount++;
        return projectiles;
    }

    public void upgradeFireRate() {
        if (upgradeFireRatePurchased < 5){
            upgradeFireRateCost = upgradeFireRateCost * 2;
            firerate = firerate * 3 / 4;
            upgradeFireRatePurchased++;
        }

    }
    public void upgradeRange() {
        if (upgradeRangePurchased < 5){
            upgradeRangeCost = upgradeRangeCost * 2;
            range = range + 20;
            upgradeRangePurchased++;
        }

    }
    public void upgradeDamage() {
        if (upgradeDamagePurchased < 5){
            upgradeDamageCost = upgradeDamageCost * 2;
            damage = damage + 1;
            upgradeDamagePurchased++;
        }

    }
    public void upgradePierce(){
        if (upgradePiercePurchased < 5){
            upgradePierceCost = upgradePierceCost * 2;
            pierce = pierce + 1;
            upgradePiercePurchased++;
        }

    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(towerColor);
        g.fillRect((int) getLocation().x-20,(int) getLocation().y-20,40,40);
    }

//    public void drawRange(Graphics2D g){
//        g.setColor(Color.black);
//        g.drawOval((int) location.x-range,(int) location.y-range,2*range,2*range);
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public Vector getTarget() {
        return target;
    }

    public void setTarget(Vector target) {
        this.target = target;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void setFirerate(int firerate) {
        this.firerate = firerate;
    }

    public int getFirerate() {
        return firerate;
    }

    public boolean willShoot() {
        return willShoot;
    }

    public void setPierce(int pierce) {
        this.pierce = pierce;
    }
    public int getPierce() {
        return pierce;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public Color getTowerColor() {
        return towerColor;
    }

    public void setTowerColor(Color towerColor) {
        this.towerColor = towerColor;
    }

    public void setTowerType(TowerType towerType) {
        this.towerType = towerType;
    }

    public TowerType getTowerType() {
        return towerType;
    }

    public int getUpgradeDamagePurchased() {
        return upgradeDamagePurchased;
    }

    public int getUpgradePiercePurchased() {
        return upgradePiercePurchased;
    }

    public int getUpgradeFireRatePurchased() {
        return upgradeFireRatePurchased;
    }

    public int getUpgradeRangePurchased() {
        return upgradeRangePurchased;
    }

    public int getUpgradePierceCost() {
        return upgradePierceCost;
    }

    public void setUpgradePierceCost(int upgradePierceCost) {
        this.upgradePierceCost = upgradePierceCost;
    }

    public int getUpgradeFireRateCost() {
        return upgradeFireRateCost;
    }

    public void setUpgradeFireRateCost(int upgradeFireRateCost) {
        this.upgradeFireRateCost = upgradeFireRateCost;
    }

    public int getUpgradeDamageCost() {
        return upgradeDamageCost;
    }

    public void setUpgradeDamageCost(int upgradeDamageCost) {
        this.upgradeDamageCost = upgradeDamageCost;
    }

    public int getUpgradeRangeCost() {
        return upgradeRangeCost;
    }

    public void setUpgradeRangeCost(int upgradeRangeCost) {
        this.upgradeRangeCost = upgradeRangeCost;
    }

    public int getShootableFrameCount() {
        return shootableFrameCount;
    }

    public void setShootableFrameCount(int shootableFrameCount) {
        this.shootableFrameCount = shootableFrameCount;
    }
    public void addShootableFrameCount(){
        shootableFrameCount++;
    }
}
