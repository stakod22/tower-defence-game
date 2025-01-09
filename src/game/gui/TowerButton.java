package game.gui;

import game.framework.Vector;
import game.towers.Tower;

import java.awt.*;

public class TowerButton extends Button{
    private int id;

    public TowerButton(Tower tower, int id) {
        super(new Vector(tower.getLocation().x-20,tower.getLocation().y-20),40, 40, "", tower.getTowerColor(), "TowerPlaced");
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
