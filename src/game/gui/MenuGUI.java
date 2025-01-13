package game.gui;

import game.framework.Drawable;
import game.framework.Vector;
import game.path.GamePath;
import game.towers.Tower;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuGUI implements Drawable {
    private boolean menuOpen = false;
    private String menuSide = "";
    private Tower tower;
    private List<Button> buttons;
    private TowerButton tb;

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        if(menuOpen){
            drawRange(g);
            if(menuSide.equals("LEFT")) {
                g.setColor(new Color(192, 192, 192));
                g.fillRect(0, 0, 200, 800);
                buttons.add(new Button(new Vector(160, 20), 20, 20, "X", Color.red, "ExitUpgradeMenu"));
            } else if (menuSide.equals("RIGHT")) {
                g.setColor(new Color(192, 192, 192));
                g.fillRect(600, 0, 200, 800);
                buttons.add(new Button(new Vector(760, 20), 20, 20, "X", Color.red, "ExitUpgradeMenu"));
            }
            g.setColor(Color.gray);
            g.fillRect(800,0,200,800);
            g.setColor(Color.BLACK);
        }
    }

    public void openUpgradeMenu(String menuSide, Tower tower, List<Button> buttons, TowerButton tb){
        menuOpen = true;
        this.menuSide = menuSide;
        this.tower = tower;
        this.buttons = buttons;
        tb.changeShowRange();
        this.tb = tb;
    }

    public void closeUpgradeMenu(){
        menuOpen = false;
        for(int i = 0; i< buttons.size();i++){
            if(buttons.get(i).getName().equals("ExitUpgradeMenu")){
                buttons.remove(i);
                i--;
            }
        }
        tb.changeShowRange();
    }

    public boolean isMenuOpen() {
        return menuOpen;
    }

    public void drawRange(Graphics2D g){
        g.drawOval(tb.getCorner().x+20-tower.getRange(),tb.getCorner().y+20-tower.getRange(),tower.getRange()*2,tower.getRange()*2);
        g.setColor(Color.BLACK);
    }
}
