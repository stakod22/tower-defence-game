package game.gui;

import game.framework.Drawable;
import game.framework.Vector;

import java.awt.*;

public class Button implements Drawable {
    Vector corner;
    int width;
    int height;
    String text;
    Color color;
    String name;

    public Button(Vector corner, int width, int height, String text, Color color, String name) {
        this.corner = corner;
        this.width = width;
        this.height = height;
        this.text = text;
        this.color = color;
        this.name = name;
    }

    public boolean pressedButton(int x, int y){
        return (corner.x < x && corner.x + width > x && corner.y < y && corner.y + height > y);
    }



    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect((int)corner.x, (int)corner.y, width, height);

        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int textX = (int)corner.x + (width - textWidth) / 2;
        int textY = (int)corner.y + (height + textHeight) / 2 - fm.getDescent();

        g.drawString(text, textX, textY);
    }


    public String getName() {
        return name;
    }

    public Vector getCorner() {
        return corner;
    }

    public void setText(String text){
        this.text = text;
    }
}
