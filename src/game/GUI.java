package game;

import java.awt.*;

public class GUI implements Drawable{
    private boolean placingmode = false;
    private Vector mouseGrid;
    private boolean cellEmpty = false;
    private boolean declinePlace = false;
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
        if(placingmode&&cellEmpty&&mouseGrid.x<=15 && mouseGrid.y<=15){

            g.fillRect(5+mouseGrid.x*50,5+mouseGrid.y*50,40,40);
            g.setColor(Color.BLACK);
        }
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
}
