package game.gui;

import game.TowerDefenceGame;
import game.framework.Screen;
import game.framework.Vector;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainMenu {
    private Button startButton;
    private Button selectLevelButton;
    private Button grassLevelButton;
    private Button waterLevelButton;
    private Button lavaLevelButton;
    private Button exitButton;
    private boolean levelSelecting = false;

    private ArrayList<Button> buttons = new ArrayList<>();
    private int counter = 0;

    private Image[] backgroundLayers;
    private int[] backgroundX;
    private int[] backgroundSpeeds;
    private Image titleImage;

    public MainMenu() {
        startButton = new Button(new Vector(250, 300), 500, 80, "Start Game", new Color(70, 213, 16), "start");
        selectLevelButton = new Button(new Vector(250, 400), 500, 80, "Select Level", new Color(86, 140, 181), "select");
        grassLevelButton = new Button(new Vector(100, 400), 200, 200, "Grass Level", new Color(34, 139, 34), "grass");
        waterLevelButton = new Button(new Vector(400, 400), 200, 200, "Water Level", new Color(30, 144, 255), "water");
        lavaLevelButton = new Button(new Vector(700, 400), 200, 200, "Lava Level", new Color(255, 69, 0), "lava");
        exitButton = new Button(new Vector(200, 650), 600, 60, "Exit Game", new Color(139, 0, 0), "exit");

        buttons.add(startButton);
        buttons.add(selectLevelButton);
        buttons.add(grassLevelButton);
        buttons.add(waterLevelButton);
        buttons.add(lavaLevelButton);
        buttons.add(exitButton);

        String[] layerPaths = {"src//res//1.png", "src//res//2.png", "src//res//3.png", "src//res//4.png"};
        backgroundLayers = new Image[layerPaths.length];
        backgroundX = new int[layerPaths.length];
        backgroundSpeeds = new int[layerPaths.length];

        for (int i = 0; i < layerPaths.length; i++) {
            backgroundLayers[i] = new ImageIcon(layerPaths[i]).getImage();
            backgroundX[i] = 0;
            backgroundSpeeds[i] = (i + 1) * 1; // Adjust speed for parallax effect
        }

        titleImage = new ImageIcon("src//res//title.png").getImage();
    }

    public void draw(Graphics2D g) {
        for (int i = 0; i < backgroundLayers.length; i++) {
            backgroundX[i] -= backgroundSpeeds[i];
            if (backgroundX[i] <= -1000) {
                backgroundX[i] = 0;
            }
            if (i == 1 || i == 2) {
                g.drawImage(backgroundLayers[i], backgroundX[i], 0, 1000, 900, null);
                g.drawImage(backgroundLayers[i], backgroundX[i] + 1000, 0, 1000, 900, null);
            } else {
                g.drawImage(backgroundLayers[i], backgroundX[i], 0, 1000, 800, null);
                g.drawImage(backgroundLayers[i], backgroundX[i] + 1000, 0, 1000, 800, null);
            }
        }

        if (!levelSelecting) {
            g.drawImage(titleImage, 50, 100, 900, 80, null);
            g.setFont(new Font("SansSerif", Font.BOLD, 40));
            startButton.draw(g);
            selectLevelButton.draw(g);
            exitButton.draw(g);
        } else {
            g.setColor(Color.BLACK);
            g.setFont(new Font("SansSerif", Font.BOLD, 60));
            FontMetrics fm = g.getFontMetrics();
            int textWidth = fm.stringWidth("Select a Level");
            g.drawString("Select a Level", (1000 - textWidth) / 2, 200);
            g.setFont(new Font("SansSerif", Font.BOLD, 30));
            if (counter < 20) {
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
                if (!levelSelecting) {
                    switch (button.getName()) {
                        case "start":
                            TowerDefenceGame.level = 1;
                            break;
                        case "exit":
                            System.exit(0);
                            break;
                        case "select":
                            levelSelecting = true;
                            break;
                    }
                } else if (counter == 20) {
                    switch (button.getName()) {
                        case "grass":
                            TowerDefenceGame.level = 1;
                            break;
                        case "water":
                            TowerDefenceGame.level = 2;
                            break;
                        case "lava":
                            TowerDefenceGame.level = 3;
                            break;
                    }
                }
            }
        }
    }
}