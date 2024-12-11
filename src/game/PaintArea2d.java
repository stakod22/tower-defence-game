package game;

import javax.swing.*;
import java.awt.*;

public class PaintArea2d extends JPanel {

    private TowerDefenceGame game = null;

    public PaintArea2d() {
        super();
        game = new TowerDefenceGame();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        int height = getHeight();
        int width = getWidth();
        Graphics2D g2d = (Graphics2D) g;
        game.update();
        game.draw(g2d);
    }
}