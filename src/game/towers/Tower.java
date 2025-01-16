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
    private int upgradeCost = 10;
    private int upgradesPurchased = 0;

    private TowerType towerType;


    public Tower(Vector location,int range) {
        this.location = location;
        this.range = range;
    }

    abstract Projectile shootProjectile();


    private void seeEnemies(EnemyList enemies){
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

    public void upgradeFirerate(){
        upgradeCost = upgradeCost * 3 / 2;
        firerate = firerate * 3 / 4;
        upgradesPurchased++;
    }
    public void upgradeRange(){
        upgradeCost = upgradeCost * 3 / 2;
        range = range +20;
        upgradesPurchased++;
    }
    public void upgradePierce(){
        upgradeCost = upgradeCost * 3 / 2;
        pierce = pierce +1;
        upgradesPurchased++;
    }
    public void upgradeDamage(){
        upgradeCost = upgradeCost * 3 / 2;
        damage = damage +1;
        upgradesPurchased++;
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(towerColor);
        g.fillRect(getLocation().x-20,getLocation().y-20,40,40);
    }

//    public void drawRange(Graphics2D g){
//        g.setColor(Color.black);
//        g.drawOval(location.x-range,location.y-range,2*range,2*range);
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

    public int getUpgradeCost() {
        return upgradeCost;
    }
}
