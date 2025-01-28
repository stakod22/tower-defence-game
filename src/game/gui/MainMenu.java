package game.gui;

import game.TowerDefenceGame;
import game.framework.Screen;
import game.framework.Vector;

import java.awt.*;
import java.util.ArrayList;

public class MainMenu {
    // Buttons for the menu
    private Button startButton;
    private Button grassLevelButton;
    private Button waterLevelButton;
    private Button lavaLevelButton;
    private Button exitButton;

    private ArrayList<Button> buttons = new ArrayList<>();

    public MainMenu() {
        // Initialize buttons with their positions, sizes, labels, and colors
        startButton = new Button(new Vector(200, 200), 600, 100, "Start Game", new Color(70, 213, 16), "start");
        grassLevelButton = new Button(new Vector(200, 350), 600, 100, "Grass Level", new Color(34, 139, 34), "grass");
        waterLevelButton = new Button(new Vector(200, 470), 600, 100, "Water Level", new Color(30, 144, 255), "water");
        lavaLevelButton = new Button(new Vector(200, 590), 600, 100, "Lava Level", new Color(255, 69, 0), "lava");
        exitButton = new Button(new Vector(200, 710), 600, 60, "Exit Game", new Color(139, 0, 0), "exit");

        // Add buttons to the list for easier iteration
        buttons.add(startButton);
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
        g.setColor(new Color(255, 223, 0)); // Golden color
        g.setFont(new Font("SansSerif", Font.BOLD, 60));
        g.drawString("Tower Defence Game", 200, 100);

        // Draw all buttons
        for (Button button : buttons) {
            button.draw(g);
        }
    }

    public void handleClick(int x, int y) {
        // Check which button is pressed and handle accordingly
        for (Button button : buttons) {
            if (button.pressedButton(x, y)) {
                switch (button.getName()) {
                    case "start":
                        TowerDefenceGame.screen = Screen.GAME;
                        break;
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
                    case "exit":
                        System.exit(0);
                        break;
                }
            }
        }
    }
}
