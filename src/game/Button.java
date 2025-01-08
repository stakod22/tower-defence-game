package game;

import java.awt.*;

public class Button implements Drawable {
    Vector corner;
    int width;
    int height;
    String text;
    Color color = Color.red;
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
        if(corner.x<x&&corner.x+width>x&&corner.y<y&&corner.y+height>y){
            return true;
        }
        return false;
    };



    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillRect(corner.x, corner.y, width, height);

        g.setColor(Color.BLACK);
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        int textX = corner.x + (width - textWidth) / 2;
        int textY = corner.y + (height + textHeight) / 2 - fm.getDescent();

        g.drawString(text, textX, textY);
    }


    public String getName() {
        return name;
    }
}
