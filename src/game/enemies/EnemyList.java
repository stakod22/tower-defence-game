package game.enemies;

import game.framework.Vector;

import java.util.ArrayList;
import java.util.List;

public class EnemyList{
    private List<Enemy> enemyList;
    private int damageThisRound = 0;
    private int moneyThisRound = 0;

    public EnemyList(){
        enemyList = new ArrayList<>();
    }

    public void addEnemy(Enemy enemy){
        enemyList.add(enemy);
    }

    public List<Enemy> getEnemyList() {
        return enemyList;
    }

    public List<Enemy> getSortedEnemyList(String param, boolean asTower){
        List<Enemy> newList = enemyList;
        switch (param){
            case "distance":
                newList.sort((a, b) -> (int) (b.getDistanceTraveled()-a.getDistanceTraveled()));
                break;
            case "health":
                newList.sort((a, b) -> b.getHealth()-a.getHealth());
                break;
            default:
                return newList;
        }
        if(asTower){
            newList.sort((a, b) -> {
                if (a.getEnemyType() == EnemyType.DISTRACTION) {
                    if (b.getEnemyType() != EnemyType.DISTRACTION) {
                        return -1;
                    }else{
                        return 0;
                    }
                }
                return 1;
            });

        }
        return newList;
    }

    public List<Vector> getEnemyLocation(){
        List<Enemy> sortedList = getSortedEnemyList("distance", false);
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
        for (Enemy e : enemyList) {
            if (e.getLocation().distanceToOther(position) <= range + e.getSize()/2.f) {
                enemies.addEnemy(e);
            }
        }
        return enemies;
    }

    private void deleteDeadEnemys(){

        for(int i = 0;i<enemyList.size();i++){
            if(enemyList.get(i).getHealth() <= 0){
                if (enemyList.get(i).getHealth() == -9999999){
                    damageThisRound += enemyList.get(i).getDamage();
                } else if (enemyList.get(i).getHealth() <= 0){
                    moneyThisRound += enemyList.get(i).getMoneyToGive();
                }
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
    public int giveMoney(){
        int returner = moneyThisRound;
        moneyThisRound = 0;
        return returner;
    }

    public void clear(){
        enemyList = new ArrayList<>();
    }

    public void update(){
        deleteDeadEnemys();
    }

    public int getNumberOfEnemys(){
        return enemyList.size();
    }

    public void clearButNotNew(){
        enemyList.clear();
    }

}
