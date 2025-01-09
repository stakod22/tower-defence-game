package game.wave;

import game.enemies.Enemy;

import java.util.ArrayList;
import java.util.List;

public class Wave {
    List<Enemy> enemies = new ArrayList<>();

    public Wave(List<Enemy> e){
        enemies = e;
    }

    public void addEnemies(Enemy enemy){
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public String toString() {
        String stringer = "";
        for (Enemy e : enemies) {
            stringer += e.toString();
            stringer += "\n";
        }
        return stringer;
    }
}
