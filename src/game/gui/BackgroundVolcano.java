package game.gui;

import game.framework.Drawable;
import game.framework.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BackgroundVolcano implements Drawable {
    private final List<Vector> lavaCracks;
    private List<SizeAbleObject> moltenRocks = new ArrayList<>();;
    private final List<Vector> fierySparks;
    private final List<Vector> smokeClouds;
    private final Random random;
    private int animationTickSpark;
    private static final int ANIMATION_SPEED_DIVISOR = 20; // Slows down animations by a factor of 20

    int length = 0;
    int angle = 0;

    int sizeSmoke = 0;

    public BackgroundVolcano() {
        this.random = new Random();
        this.lavaCracks = generateRandomVectors(50, 800, 800);
        this.fierySparks = generateRandomVectors(150, 800, 800);
        this.smokeClouds = generateRandomVectors(20, 800, 800);
        this.animationTickSpark = 0;
        for(int i = 0;i<75;i++){
            moltenRocks.add(new SizeAbleObject());
        }
    }

    private List<Vector> generateRandomVectors(int count, int maxX, int maxY) {
        List<Vector> vectors = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            vectors.add(new Vector(random.nextInt(maxX), random.nextInt(maxY)));
        }
        return vectors;
    }

    @Override
    public void update() {
        animationTickSpark++;
        if (animationTickSpark == ANIMATION_SPEED_DIVISOR) {
            animationTickSpark = 0;
            for (Vector spark : fierySparks) {
                spark.x += random.nextInt(5) - 2; // Slight horizontal jitter
                spark.y += random.nextInt(3) - 1; // Slight vertical jitter

                if (spark.x < 0 || spark.x > 800 || spark.y < 0 || spark.y > 800) {
                    spark.x = random.nextInt(800);
                    spark.y = random.nextInt(800);
                }
            }
        }
    }

    @Override
    public void draw(Graphics2D g) {
        drawLavaBackground(g);
        //drawLavaCracks(g);
        drawMoltenRocks(g);
        drawFierySparks(g);
        //drawSmokeClouds(g);
    }

    private void drawLavaBackground(Graphics2D g) {
        GradientPaint lavaGradient = new GradientPaint(0, 0, new Color(255, 30, 0), 0, 800, new Color(251, 142, 0));
        g.setPaint(lavaGradient);
        g.fillRect(0, 0, 800, 800);
    }

    private void drawLavaCracks(Graphics2D g) {
        g.setColor(new Color(50, 50, 50));
        for (Vector crack : lavaCracks) {
            int length = random.nextInt(30) + 20;
            int angle = random.nextInt(360);
            int x2 = (int) (crack.x + length * Math.cos(Math.toRadians(angle)));
            int y2 = (int) (crack.y + length * Math.sin(Math.toRadians(angle)));
            g.drawLine((int) crack.x, (int) crack.y, x2, y2);
        }
    }

    private void drawMoltenRocks(Graphics2D g) {
        for (SizeAbleObject rock : moltenRocks) {
            if(rock.updateTicker()){
                rock.setSize(random.nextInt(10) + 15);
            }
            g.setColor(new Color(139, 69, 19));
            g.fillOval((int) rock.getPosition().x - rock.getSize() / 2, (int) rock.getPosition().y - rock.getSize() / 2, rock.getSize(), rock.getSize());
            g.setColor(new Color(255, 99, 71));
            g.fillOval((int) rock.getPosition().x - rock.getSize() / 4, (int) rock.getPosition().y - rock.getSize() / 4, rock.getSize() / 2, rock.getSize() / 2);
        }
    }

    private void drawFierySparks(Graphics2D g) {
        for (Vector spark : fierySparks) {
            g.setColor(new Color(255, 215, 0, 200));
            g.fillOval((int) spark.x, (int) spark.y, 4, 4);
        }
    }

    private void drawSmokeClouds(Graphics2D g) {
        for (Vector cloud : smokeClouds) {
            sizeSmoke = random.nextInt(40) + 30;
            g.setColor(new Color(105, 105, 105, 100));
            g.fillOval((int) cloud.x - sizeSmoke / 2, (int) cloud.y - sizeSmoke / 2, sizeSmoke, sizeSmoke);
        }
    }


    public class SizeAbleObject {
        Vector position;
        int size;
        int ticker;
        int maxTicker;

        public SizeAbleObject() {
            position = new Vector(random.nextInt(800), random.nextInt(800));
            size = random.nextInt(10) + 15;
            ticker = 0;
            maxTicker = random.nextInt(30) + 20;
        }

        public void setSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public Vector getPosition() {
            return position;
        }

        public boolean updateTicker() {
            ticker++;
            if (ticker >= maxTicker) {
                ticker = 0;
                return true;
            }
            return false;
        }
    }
}
