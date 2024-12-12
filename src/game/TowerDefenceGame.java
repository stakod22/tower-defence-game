package game;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class TowerDefenceGame {
    public static final int CELL_WIDTH = 50;
    public static final int CELL_HEIGHT = 50;

    private List<Drawable> figures = new ArrayList<>();

    public TowerDefenceGame(){
        figures.add(new StandartEnemy(figures.size()-1,new Vector(100,20)));
    }

    public void update(){
        for(Drawable d : figures) {
            d.update();
        }
    }

    public void draw(Graphics2D g){
        for(Drawable d : figures) {
            d.draw(g);
        }
    }


}
