package game.towers;

import game.TowerDefenceGame;
import game.projectiles.Projectile;
import game.enemies.EnemyList;

import java.util.ArrayList;
import java.util.List;

public class TowerList {
    private List<Tower> towerList;
    private int currentID = 1;
    public TowerList(){
        towerList = new ArrayList<>();
    }

    public List<Tower> getTowerList() {
        return towerList;
    }

    public void setTowerList(List<Tower> towerList) {
        this.towerList = towerList;
    }
    public void addTower(Tower e){
        e.setId(currentID);
        towerList.add(e);
        currentID++;
    }

    public List<Projectile> update(EnemyList e){
        List<Projectile> projectiles = new ArrayList<>();
        for (Tower t : towerList){
            projectiles.addAll(t.update(e));
        }
        return projectiles;
    }

    public Tower getTowerByCell(int cellX, int cellY){
        for (Tower t : towerList){
            if(((int)t.getLocation().x)/TowerDefenceGame.CELL_WIDTH == cellX && (int)(t.getLocation().y)/ TowerDefenceGame.CELL_HEIGHT == cellY){
                return t;
            }
        }
        return null;
    }
    public void clear(){
        towerList.clear();
    }
    public void remove(Tower tower){
        towerList.remove(tower);
    }
}
