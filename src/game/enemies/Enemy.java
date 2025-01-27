package game.enemies;

import game.framework.Drawable;
import game.path.GamePath;
import game.path.PathSegment;
import game.framework.Vector;
import game.projectiles.StatusEffect;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Enemy implements Drawable {
    static int count = 1;
    private int id;
    private Vector location;
    private Vector speed;
    private int health;
    private int size;
    private int currentSegment = 0;
    private List<PathSegment> segments;
    private int moneyToGive;
    private int damage = 0;
    private List<StatusEffect> statusEffects = new ArrayList<>();
    private Color color;
    private float currentMoveWay = 0;
    private float speedValue;
    private float distanceTraveled;
    private EnemyType enemyType;

    public Enemy(){

    }

    public float getSpeedValue() {
        return speedValue;
    }

    public void setRealSpeedValue(float speedValue) {
        this.speedValue = speedValue;
        speed = new Vector(0,speedValue);
    }

    public int getCurrentSegment() {
        return currentSegment;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public List<PathSegment> getSegments() {
        return segments;
    }

    public void setSegments(List<PathSegment> segments) {
        this.segments = segments;
    }

    @Override
    public void update() {
        boolean frozen = false;
        float remainingSpeed = speedValue;
        float currentSpeed;

        for (int i = 0; i < statusEffects.size(); i++) {
            switch(statusEffects.get(i).damageType) {
                case FREEZE:
                    if(!frozen){
                        remainingSpeed /= 2.f;
                        frozen = true;
                    }
                    break;
            }
            statusEffects.get(i).damageDuration--;
            if(statusEffects.get(i).damageDuration <= 0){
                statusEffects.remove(i);
            }
        }

        do {
            if(segments.get(currentSegment).lenght<=currentMoveWay+remainingSpeed){
                currentSpeed = segments.get(currentSegment).lenght-currentMoveWay;
                remainingSpeed -= currentSpeed;
            }else{
                currentSpeed = remainingSpeed;
                remainingSpeed = 0;
            }

            switch (segments.get(currentSegment).direction){
                case 1:
                    speed = new Vector(0,-currentSpeed);
                    break;
                case 2:
                    speed = new Vector(currentSpeed,0);
                    break;
                case 3:
                    speed = new Vector(0,currentSpeed);
                    break;
                case 4:
                    speed = new Vector(-currentSpeed,0);
                    break;
                case 5:
                    damage = health;
                    health = -9999999;
                    break;
            }

            if(location.y>=0 && location.x >= 0){
                currentMoveWay+=Math.abs(speed.x);
                currentMoveWay+=Math.abs(speed.y);

                distanceTraveled += Math.abs(speed.x);
                distanceTraveled += Math.abs(speed.y);
            }

            if(segments.get(currentSegment).lenght<=currentMoveWay) {
                currentMoveWay = 0.f;
                currentSegment++;
            }

            location.add(speed);
        }while(remainingSpeed > 0);
    }

    @Override
    public void draw(Graphics2D g) {
        boolean frozen = false;
        g.setColor(getColor());
        g.fillRect((int) location.x-size/2,(int) location.y-size/2, size, size);

        for (StatusEffect statusEffect : statusEffects) {
            switch (statusEffect.damageType) {
                case FREEZE:
                    if (!frozen) {
                        frozen = true;
                        g.setColor(new Color(49, 199, 245, 64));
                        g.fillRect((int) location.x - size / 2, (int) location.y - size / 2, size, size);
                    }
                    break;
            }
        }
        g.setColor(Color.black);
    }
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

    public float getDistanceTraveled() {
        return distanceTraveled;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void getHit(int damage){
        health -= damage;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public int getDamage() {
        return damage;
    }

    public int getMoneyToGive() {
        return moneyToGive;
    }

    public void setMoneyToGive(int moneyToGive) {
        this.moneyToGive = moneyToGive;
    }

    public void addStatusEffect(StatusEffect statusEffect) {
        this.statusEffects.add(statusEffect);
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    @Override
    public String toString() {
        String stringer = "";
        stringer += "Enemy: "+ id + ", Type: " + enemyType + ", Health: "+ health + ", TraveledDistance:" + distanceTraveled;
        return stringer;
    }

    public static class Builder{
        private int id;
        private Vector location;
        private float speedValue;
        private int health;
        private int size;
        private int moneyToGive;
        private Color color;
        private List<PathSegment> segments;
        private EnemyType enemyType;

        public Builder(){
            id = Enemy.count;
            Enemy.count++;
        }

        public Enemy.Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Enemy.Builder setLocation(Vector location){
            this.location = location;
            this.location.add(new Vector(GamePath.pathSize, GamePath.pathSize));
            return this;
        }

        public Enemy.Builder setSpeedValue(float speed){
            this.speedValue = speed;
            return this;
        }

        public Enemy.Builder setHealth(int health){
            this.health = health;
            return this;
        }

        public Enemy.Builder setSize(int size){
            this.size = size;
            return this;
        }

        public Enemy.Builder setMoneyToGive(int moneyToGive){
            this.moneyToGive = moneyToGive;
            return this;
        }

        public Enemy.Builder setColor(Color color){
            this.color = color;
            return this;
        }

        public Enemy.Builder setSegments(List<PathSegment> segments){
            this.segments = segments;
            return this;
        }

        public Enemy.Builder setEnemyType(EnemyType type){
            this.enemyType = type;
            return this;
        }

        public void finalizeBuild(Enemy e){
            e.setId(id);
            e.setLocation(location);
            e.setRealSpeedValue(speedValue);
            e.setHealth(health);
            e.setSize(size);
            e.setMoneyToGive(moneyToGive);
            e.setColor(color);
            e.setSegments(segments);
            e.setEnemyType(enemyType);
        }

        public Enemy.Builder buildFrom(Enemy e){
            this.id = e.getId();
            if(e.getLocation()!=null){
                this.location = e.getLocation();
            }

            this.speedValue = e.getSpeedValue();
            this.health = e.getHealth();
            this.size = e.getSize();
            this.moneyToGive = e.getMoneyToGive();
            if(e.getColor() != null){
                this.color = e.getColor();
            }
            if(e.getSegments() != null){
                this.segments = e.getSegments();
            }
            if(e.getEnemyType() != null){
                this.enemyType = e.getEnemyType();
            }
            return this;
        }
    }
}
