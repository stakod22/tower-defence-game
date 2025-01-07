package game;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

abstract class Enemy implements Drawable{
    private int id;
    private Vector location;
    private Vector speed;
    private int health;
    private int distanceTraveled;
    List<Turn> turns = new ArrayList<>();

    public Enemy(Vector location, int health) {
        this.location = location;
        this.distanceTraveled = 0;
        this.health = health;
        loadTurns();
    }

    @Override
    public void update() {

        for(Turn turn : turns) {

            //if(>=turn.turnPosition.x &&turn.turnPosition.y==)
        }
        location.add(speed);
        distanceTraveled += speed.x;
        distanceTraveled += speed.y;
    }
    @Override
    public void draw(Graphics2D g) {

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

    public Vector getSpeed() {
        return speed;
    }

    public void setSpeed(Vector speed) {
        this.speed = speed;
    }

    public int getDistanceTraveled() {
        return distanceTraveled;
    }

    public void setDistanceTraveled(int distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }



    public void loadTurns(){
        turns.add(new Turn(new Vector(250,200), "EAST" ));
    }

    public class Turn{
        Vector turnPosition;
        String direction; //When turn mult the absolute Value of x and y with given speed from turn c:   :)

        public Turn(Vector turnPosition, String direction){
            this.turnPosition = turnPosition;
            this.direction = direction;
        }

        public Vector getTurnPosition() {
            return turnPosition;
        }

        public void setTurnPosition(Vector turnPosition) {
            this.turnPosition = turnPosition;
        }

        public String getDirection() {
            return direction;
        }

        public void setDirection(String direction) {
            this.direction = direction;
        }
    }
}
