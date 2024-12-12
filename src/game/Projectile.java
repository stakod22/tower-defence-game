package game;

import java.awt.*;

public abstract class Projectile implements Drawable{
    private int id;
    private Vector location;
    private Vector targetLocation;

    abstract public void doDamage(Enemy enemy);



    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {

    }
}
