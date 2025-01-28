package game.wave;

import game.enemies.Enemy;

import java.util.List;

public class Wave {
    List<Enemy> enemies;

    public Wave(List<Enemy> e){
        enemies = e;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public String toString() {
        StringBuilder stringer = new StringBuilder();
        for (Enemy e : enemies) {
            stringer.append(e.toString());
            stringer.append("\n");
        }
        return stringer.toString();
    }
}
