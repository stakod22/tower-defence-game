package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PaintArea2d extends JPanel {

    private TowerDefenceGame game;

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
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int height = getHeight();
        int width = getWidth();
        Graphics2D g2d = (Graphics2D) g;
        game.update();
        game.draw(g2d);
    }

    private void handleMousePress(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        System.out.println("Mouse clicked at: " + x + ", " + y);

        game.handleMouseClick(x, y);
    }
}
