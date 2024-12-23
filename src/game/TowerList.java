package game;

import java.util.ArrayList;
import java.util.List;

public class TowerList {
    private List<Tower> towerList;
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
        towerList.add(e);
    }

    public void updateTargeting(EnemyList e){
        for (Tower t : towerList){
            t.updateEnemy(e);
        }
    }
}
