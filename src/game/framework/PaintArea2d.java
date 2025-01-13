package game.framework;

import game.TowerDefenceGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PaintArea2d extends JPanel {

    private final TowerDefenceGame game;

    public PaintArea2d() {
        super();
        game = new TowerDefenceGame();

        // Add a mouse listener to detect clicks
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mouseClicked(e);
                handleMousePress(e);
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                handleMouseDragged(e);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        game.update();
        game.draw(g2d);
    }

    private void handleMousePress(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        game.handleMouseClick(x, y);
    }

    private void handleMouseDragged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        game.handleMouseDragged(x, y);
    }

}
