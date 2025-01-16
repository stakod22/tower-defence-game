package game.gui;

import game.framework.Drawable;
import game.framework.Vector;
import game.path.GamePath;
import game.towers.Tower;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GUI implements Drawable {
    private boolean placingmode = false;
    private Vector mouseGrid;
    private boolean cellEmpty = false;
    private boolean declinePlace = false;
    private int towerRange = 0;

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        for(int i = 0; i<=15;i++){
            for(int j = 0; j<=15;j++){
                g.drawRect(j*50,i*50,50,50);
            }
        }
        g.setColor(Color.gray);
        g.fillRect(800,0,200,800);
        g.setColor(Color.BLACK);


        if(declinePlace){
            g.setColor(Color.RED);
            declinePlace = false;
        }else{
            g.setColor(Color.GREEN);
        }
        GamePath gamePath = new GamePath();
        if(placingmode&&cellEmpty&&mouseGrid.x<=15 && mouseGrid.y<=15&& !gamePath.isOnPath((int)mouseGrid.x, (int)mouseGrid.y)){

            g.fillRect((int) (5+mouseGrid.x*50),(int) (5+mouseGrid.y*50),40,40);
            g.setColor(Color.BLACK);
            drawRange(g, new Vector(mouseGrid.x*50+25,mouseGrid.y*50+25));
        }
    }




    public void drawRange(Graphics2D g, Vector loc){
        g.drawOval((int)loc.x-towerRange,(int)loc.y-towerRange,towerRange*2,towerRange*2);
        g.setColor(Color.BLACK);
    }


    public void setPlacingmode(boolean placingmode) {
        this.placingmode = placingmode;
    }

    public void setMouse(Vector mouse) {
        this.mouseGrid = mouse;
    }
    public void setCellEmpty(boolean cellEmpty) {
        this.cellEmpty =  cellEmpty;
    }

    public void setDeclinePlace(boolean declinePlace) {
        this.declinePlace = declinePlace;
    }
    public void setTowerRange(int towerRange) {
        this.towerRange = towerRange;
    }
}
