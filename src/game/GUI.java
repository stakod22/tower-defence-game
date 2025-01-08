package game;

import java.awt.*;

public class GUI implements Drawable{
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
    }
}
