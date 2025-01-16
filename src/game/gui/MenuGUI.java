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
            } else if (menuSide.equals("RIGHT")) {
                g.setColor(new Color(192, 192, 192));
                g.fillRect(600, 0, 200, 800);
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
        initMenuButtons();
    }

    public void closeUpgradeMenu(){
        menuOpen = false;
        for(int i = 0; i < 5;i++){
            buttons.remove(buttons.size()-1);
        }
        tb.changeShowRange();
    }

    public boolean isMenuOpen() {
        return menuOpen;
    }

    public void drawRange(Graphics2D g){
        g.drawOval((int)tb.getCorner().x+20-tower.getRange(),(int)tb.getCorner().y+20-tower.getRange(),tower.getRange()*2,tower.getRange()*2);
        g.setColor(Color.BLACK);
    }
    public void initMenuButtons(){
        if(menuSide.equals("LEFT")) {
            buttons.add(new Button(new Vector(160, 20), 20, 20, "X", Color.red, "ExitUpgradeMenu"));

            buttons.add(new Button(new Vector(25, 75),150,75,"Fire rate", Color.orange, "FireRateUpgrade"));
            buttons.add(new Button(new Vector(25, 175),150,75,"Range", new Color(79, 181, 55, 255), "RangeUpgrade"));
            buttons.add(new Button(new Vector(25, 275),150,75,"Damage", Color.red, "DamageUpgrade"));
            buttons.add(new Button(new Vector(25, 375),150,75,"Pierce", new Color(99, 6, 6), "PierceUpgrade"));
        } else if (menuSide.equals("RIGHT")) {
            buttons.add(new Button(new Vector(760, 20), 20, 20, "X", Color.red, "ExitUpgradeMenu"));

            buttons.add(new Button(new Vector(625, 75),150,75,"Fire rate", Color.orange, "FireRateUpgrade"));
            buttons.add(new Button(new Vector(625, 175),150,75,"Range", new Color(79, 181, 55, 255), "RangeUpgrade"));
            buttons.add(new Button(new Vector(625, 275),150,75,"Damage", Color.red, "DamageUpgrade"));
            buttons.add(new Button(new Vector(625, 375),150,75,"Pierce", new Color(99, 6, 6), "PierceUpgrade"));
        }

    }
}
