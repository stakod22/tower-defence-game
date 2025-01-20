package game.projectiles;

import game.enemies.Enemy;
import game.framework.Drawable;
import game.framework.Vector;
import game.enemies.EnemyList;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Projectile implements Drawable {
    private int id;
    private Vector location;
    private Vector targetLocation;
    private int damage;
    private int radius;
    private int speed = 35;
    private int pierce = 0;
    private double angle;
    private DamageType damageType = DamageType.NORMAL;
    private int effectDuration = 0;
    private List<Enemy> enemiesHit = new ArrayList<>();
    private Color color = Color.BLACK;

    public void doDamage(EnemyList enemies){
        List<Enemy> enemyList = enemies.getEnemyList();
        enemyList.forEach((enemy) -> {
            enemy.getHit(damage);
            hitOnce();
            addEnemyHit(enemy);
            enemy.addStatusEffect(new StatusEffect(damageType, effectDuration));
        });
    }

    
    public boolean brokeProjectile(){
        if (location.x < 0){
            return true;
        } else if (location.y < 0) {
            return true;
        } else if (location.x > 1500){
            return true;
        } else if (location.y > 1000) {
            return true;
        } else return pierce == 0;
    }
    public int getDamage() {
        return damage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Vector getLocation() {
        return location;
    }

    public void addEnemyHit(Enemy e){
        enemiesHit.add(e);
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public Vector getTargetLocation() {
        return targetLocation;
    }

    public void setTargetLocation(Vector targetLocation) {
        this.targetLocation = targetLocation;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public List<Enemy> getEnemiesHit() {
        return enemiesHit;
    }

    public void setDidDamage(int didDamage) {
        this.pierce = pierce + didDamage;
    }

    public void hitOnce(){
        pierce--;
    }

    @Override
    public void update() {
        float x2 = (float) (speed * Math.cos(angle));
        float y2 = (float) (speed * Math.sin(angle));

        location.x += (int) x2;
        location.y += (int) y2;

    }


    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.darkGray);
        g.fillOval((int) location.x-10, (int) location.y-10, radius*2, radius*2);
        g.setColor(Color.black);
    }

    public DamageType getDamageType() {
        return damageType;
    }

    public void setDamageType(DamageType damageType) {
        this.damageType = damageType;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPierce(int pierce) {
        this.pierce = pierce;
    }

    public int getPierce() {
        return pierce;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public double getAngle() {
        return angle;
    }

    public void setEffectDuration(int effectDuration) {
        this.effectDuration = effectDuration;
    }

    public int getEffectDuration() {
        return effectDuration;
    }

    public static class Builder{
        private Vector location;
        private Vector targetLocation;
        private int damage;
        private int radius;
        private int speed = 35;
        private int pierce = 0;
        private DamageType damageType = DamageType.NORMAL;
        private int effectDuration = 50;
        private Color color = Color.BLACK;

        public Builder setLocation(Vector location){
            this.location = location;
            return this;
        }

        public Builder setTargetLocation(Vector targetLocation){
            this.targetLocation = targetLocation;
            return this;
        }

        public Builder setDamage(int damage){
            this.damage = damage;
            return this;
        }

        public Builder setRadius(int radius){
            this.radius = radius;
            return this;
        }

        public Builder setSpeed(int speed){
            this.speed = speed;
            return this;
        }

        public Builder setPierce(int pierce){
            this.pierce = pierce;
            return this;
        }

        public Builder setDamageType(DamageType damageType) {
            this.damageType = damageType;
            return this;
        }

        public Builder setColor(Color color) {
            this.color = color;
            return this;
        }

        public Builder setEffectDuration(int effectDuration) {
            this.effectDuration = effectDuration;
            return this;
        }

        public Projectile build(){
            Projectile p = new Projectile();
            finalizeBuild(p);
            return p;
        }

        public Projectile buildRay(){
            Projectile p = new RayProjectile();
            finalizeBuild(p);
            return p;
        }

        public Projectile finalizeBuild(Projectile p){
            p.setLocation(location);
            p.setTargetLocation(targetLocation);
            p.setDamage(damage);
            p.setRadius(radius);
            p.setSpeed(speed);
            p.setPierce(pierce);
            p.setDamageType(damageType);
            p.setEffectDuration(effectDuration);
            p.setColor(color);
            float x1 = targetLocation.x - location.x;
            float y1 = targetLocation.y - location.y;
            p.setAngle(Math.atan2(y1, x1));

            return p;
        }

        public Projectile.Builder buildFrom(Projectile p){
            if(p.getLocation() != null){
                setLocation(p.getLocation());
            }
            if(p.getTargetLocation() != null){
                setTargetLocation(p.getTargetLocation());
            }
            setDamage(p.getDamage());
            setRadius(p.getRadius());
            setSpeed(p.getSpeed());
            setPierce(p.getPierce());
            if(p.getDamageType() != null){
                setDamageType(p.getDamageType());
            }
            setEffectDuration(p.getEffectDuration());
            if(p.getColor() != null){
                setColor(p.getColor());
            }

            return this;
        }
    }
}