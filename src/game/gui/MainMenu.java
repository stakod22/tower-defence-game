package game.gui;

import game.TowerDefenceGame;
import game.framework.Screen;
import game.framework.Vector;

import java.awt.*;
import java.util.ArrayList;

public class MainMenu {
    // Buttons for the menu
    private Button startButton;
    private Button selectLevelButton;
    private Button grassLevelButton;
    private Button waterLevelButton;
    private Button lavaLevelButton;
    private Button exitButton;
    private boolean levelSelecting = false;

    private ArrayList<Button> buttons = new ArrayList<>();
    private int counter = 0;

    public MainMenu() {
        // Initialize buttons with their positions, sizes, labels, and colors
        startButton = new Button(new Vector(250, 300), 500, 80, "Start Game", new Color(70, 213, 16), "start");
        selectLevelButton = new Button(new Vector(250,400),500,80,"Select Level", new Color(86, 140, 181), "select");

        grassLevelButton = new Button(new Vector(100, 400), 200, 200, "Grass Level", new Color(34, 139, 34), "grass");
        waterLevelButton = new Button(new Vector(400, 400), 200, 200, "Water Level", new Color(30, 144, 255), "water");
        lavaLevelButton = new Button(new Vector(700, 400), 200, 200, "Lava Level", new Color(255, 69, 0), "lava");
        exitButton = new Button(new Vector(200, 650), 600, 60, "Exit Game", new Color(139, 0, 0), "exit");

        // Add buttons to the list for easier iteration
        buttons.add(startButton);
        buttons.add(selectLevelButton);
        buttons.add(grassLevelButton);
        buttons.add(waterLevelButton);
        buttons.add(lavaLevelButton);
        buttons.add(exitButton);
    }

    public void draw(Graphics2D g) {
        // Set background color and draw the background
        g.setColor(new Color(135, 206, 250)); // Light blue sky
        g.fillRect(0, 0, 1000, 800);

        // Draw a title at the top


        // Draw all buttons
        if(!levelSelecting) {
            g.setColor(new Color(255, 223, 0)); // Golden color
            g.setFont(new Font("SansSerif", Font.BOLD, 60));
            g.drawString("Tower Defence Game", 200, 100);

            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            startButton.draw(g);
            selectLevelButton.draw(g);
            exitButton.draw(g);
        }else{
            g.setColor(new Color(0, 0, 0)); // Golden color
            String text = "Select a Level";
            g.setFont(new Font("SansSerif", Font.BOLD, 60)); // Set font before getting metrics
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth(text);
            int textX = (1000 - textWidth) / 2; // Center horizontally
            int textY = 200;

            g.drawString(text, textX, textY);


            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            if(counter<20) {
                counter++;
            }
            grassLevelButton.draw(g);
            waterLevelButton.draw(g);
            lavaLevelButton.draw(g);
        }
    }

    public void handleClick(int x, int y) {
        for (Button button : buttons) {
            if (button.pressedButton(x, y)) {
                if(!levelSelecting){
                    switch (button.getName()) {
                        case "start":
                            TowerDefenceGame.screen = Screen.GAME;
                            break;
                        case "exit":
                            System.exit(0);
                            break;
                        case "select":
                            levelSelecting = true;
                            break;
                    }
                }else if(counter==20){
                    switch (button.getName()){
                        case "grass":
                            TowerDefenceGame.level = 1;
                            TowerDefenceGame.screen = Screen.GAME;
                            break;
                        case "water":
                            TowerDefenceGame.level = 2;
                            TowerDefenceGame.screen = Screen.GAME;
                            break;
                        case "lava":
                            TowerDefenceGame.level = 3;
                            TowerDefenceGame.screen = Screen.GAME;
                            break;
                    }
                }
            }
        }
    }
}
