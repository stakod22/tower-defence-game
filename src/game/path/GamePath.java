package game.path;

import game.TowerDefenceGame;
import game.framework.Vector;
import game.framework.Drawable;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GamePath implements Drawable {
    private List<Vector> pathTiles = new ArrayList();
    private List<PathSegment> segments = new ArrayList();
    public static int pathSize = 25;


    public GamePath() {
        loadPath();
    }

    public void loadPath(){
        if(TowerDefenceGame.level==1){
            pathTiles.add(new Vector(250,0));
            pathTiles.add(new Vector(250,50));
            pathTiles.add(new Vector(250,100));
            pathTiles.add(new Vector(250,150));
            pathTiles.add(new Vector(250,200));
            segments.add(new PathSegment(225,3));

            pathTiles.add(new Vector(300,200));
            pathTiles.add(new Vector(350,200));
            pathTiles.add(new Vector(400,200));
            pathTiles.add(new Vector(450,200));
            pathTiles.add(new Vector(500,200));
            pathTiles.add(new Vector(550,200));
            pathTiles.add(new Vector(600,200));
            segments.add(new PathSegment(350,2));

            pathTiles.add(new Vector(600,250));
            pathTiles.add(new Vector(600,300));
            pathTiles.add(new Vector(600,350));
            pathTiles.add(new Vector(600,400));
            segments.add(new PathSegment(200,3));

            pathTiles.add(new Vector(550,400));
            pathTiles.add(new Vector(500,400));
            pathTiles.add(new Vector(450,400));
            pathTiles.add(new Vector(400,400));
            pathTiles.add(new Vector(350,400));
            pathTiles.add(new Vector(300,400));
            pathTiles.add(new Vector(250,400));
            pathTiles.add(new Vector(200,400));
            pathTiles.add(new Vector(150,400));
            pathTiles.add(new Vector(100,400));
            segments.add(new PathSegment(500,4));

            pathTiles.add(new Vector(100,450));
            pathTiles.add(new Vector(100,500));
            pathTiles.add(new Vector(100,550));
            pathTiles.add(new Vector(100,600));
            pathTiles.add(new Vector(100,650));
            pathTiles.add(new Vector(100,700));
            pathTiles.add(new Vector(100,750));
            segments.add(new PathSegment(400,3));

            segments.add(new PathSegment(100,5));
        } else if(TowerDefenceGame.level==2){
            pathTiles.add(new Vector(250,0));
            pathTiles.add(new Vector(250,50));
            pathTiles.add(new Vector(250,100));
            pathTiles.add(new Vector(250,150));
            pathTiles.add(new Vector(250,200));
            pathTiles.add(new Vector(250,250));
            pathTiles.add(new Vector(250,300));
            pathTiles.add(new Vector(250,350));
            segments.add(new PathSegment(375,3));

            pathTiles.add(new Vector(300,350));
            pathTiles.add(new Vector(350,350));
            pathTiles.add(new Vector(400,350));
            pathTiles.add(new Vector(450,350));
            pathTiles.add(new Vector(500,350));
            segments.add(new PathSegment(250,2));

            pathTiles.add(new Vector(500,300));
            pathTiles.add(new Vector(500,250));
            segments.add(new PathSegment(100,1));

            pathTiles.add(new Vector(550,250));
            pathTiles.add(new Vector(600,250));
            segments.add(new PathSegment(100,2));

            pathTiles.add(new Vector(600,300));
            pathTiles.add(new Vector(600,350));
            pathTiles.add(new Vector(600, 400));
            pathTiles.add(new Vector(600,450));
            pathTiles.add(new Vector(600,500));
            pathTiles.add(new Vector(600,550));
            pathTiles.add(new Vector(600,600));
            pathTiles.add(new Vector(600,650));
            pathTiles.add(new Vector(600,700));
            pathTiles.add(new Vector(600,750));
            segments.add(new PathSegment(500,3));

            segments.add(new PathSegment(100,5));


        }

    }



    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        for(Vector v : pathTiles){
            int x = (int)v.x;
            int y = (int)v.y;
            g.setColor(new Color(1, 93, 149));
            g.fillRect(x,y,50,50);
            g.setColor(Color.BLACK);
        }
    }

    public boolean isOnPath(int gridX, int gridY){
        for(Vector p : pathTiles){
            if(p.x/TowerDefenceGame.CELL_WIDTH == gridX&&p.y/TowerDefenceGame.CELL_HEIGHT == gridY){
                return true;
            }
        }
        return false;
    }

    public void updateLevel(){
        segments = new ArrayList<>();
        pathTiles = new ArrayList<>();
        loadPath();
    }

    public List<PathSegment> getSegments() {
        return segments;
    }

    public List<Vector> getPathTiles() {
        return pathTiles;
    }
}
