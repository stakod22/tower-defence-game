package game;

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

    public void update(EnemyList e){
        for (Tower t : towerList){
            t.update(e);
        }
    }
}
