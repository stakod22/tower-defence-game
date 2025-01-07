package game;

import java.util.ArrayList;
import java.util.List;

public class EnemyList{
    private List<Enemy> enemyList;
    private int damageThisRound = 0;

    public EnemyList(){
        enemyList = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public List<Enemy> getSortedEnemyList(String param){
        List<Enemy> newList = enemyList;
        switch (param){
            case "distance":
                newList.sort((a, b) -> a.getDistanceTraveled()-b.getDistanceTraveled());
                break;
            case "health":
                newList.sort((a, b) -> a.getHealth()-b.getHealth());
                break;
            default:
                return newList;
        }
        return newList;
    }

    public List<Vector> getEnemyLocation(){
        List<Enemy> sortedList = getSortedEnemyList("distance");
        List<Vector> locationList = new ArrayList<>();

        sortedList.forEach((Enemy enemy) -> {
            locationList.add(enemy.getLocation());
        });
        return locationList;
    }

    public Enemy getEnemyById(int id){
        for (Enemy enemy : enemyList) {
            if (enemy.getId() == id) {
                return enemy;
            }
        }
        return null;
    }

    public EnemyList inRange(int range, Vector position){
        EnemyList enemies = new EnemyList();
        for (Enemy enemy : enemyList) {
            if (enemy.getLocation().distanceToOther(position) <= range) {
                enemies.addEnemy(enemy);
            }
        }
        return enemies;
    }

    private void deleteDeadEnemys(){

        for(int i = 0;i<enemyList.size();i++){
            if(enemyList.get(i).getHealth() <= 0){
                damageThisRound += enemyList.get(i).getDamage();
                enemyList.remove(i);
                i--;
            }
        }
    }
    public int doDamage(){
        int returner = damageThisRound;
        damageThisRound = 0;
        return returner;
    }

    public void update(){
        deleteDeadEnemys();
    }
}
