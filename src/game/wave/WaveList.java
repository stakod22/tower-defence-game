package game.wave;

import game.TowerDefenceGame;
import game.path.GamePath;
import game.framework.Vector;
import game.enemies.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WaveList {
    private final List<Wave> waveList = new ArrayList<>();
    private int currentWave = 0;
    private int maxWave = 100;

    public WaveList(int maxWave) {
        this.maxWave = maxWave;
    }

    public void addWave(List<Enemy> e){
        waveList.add(new Wave(e));
    }

    public Wave getNextWave(){

        if (currentWave < waveList.size()&& currentWave < maxWave){
            return waveList.get(currentWave);
        }else {
            return null;
        }
    }

    public void currentWaveDone(){
        currentWave++;
    }

    public int getCurrentWave() {
        return currentWave;
    }

    public void strengthenEnemies(){
        for (int i = 0; i < waveList.size(); i++) {
            float healthincrease = 1.04f;
            float speedincrease = 1.06f;
            float moneydecrease = 0.99f;

            for (Enemy e : waveList.get(i).enemies){
                if(!(e instanceof SuperBoss100Enemy)){
                    e.setHealth((int) (e.getHealth()*(Math.pow(healthincrease,i))));
                    e.setRealSpeedValue((float) (e.getSpeedValue()*(Math.pow(speedincrease,i))));
                    e.setMoneyToGive((int) (e.getMoneyToGive()*(Math.pow(moneydecrease,i))));
                    if (e.getMoneyToGive() < 1){
                        e.setMoneyToGive(1);
                    }
                }
            }
        }
    }


    public void debug(GamePath gamePath){
        List<Enemy> waveContend1 = new ArrayList<>();
        waveContend1.add(new StandartEnemy(new Vector(250, 50), gamePath.getSegments()));
        waveContend1.add(new TankEnemy(new Vector(250, 50), gamePath.getSegments()));
        addWave(waveContend1);
    }

    public void init(GamePath gamePath){
        waveList.clear();
        currentWave = 0;
        if (TowerDefenceGame.level == 1) {
            initLevel1(gamePath);
        } else if (TowerDefenceGame.level == 2) {
            initLevel2(gamePath);
        } else if (TowerDefenceGame.level == 3) {
            initLevel3(gamePath);
        }else {
            initLevel1(gamePath);
        }
        createRandom(gamePath);

        List<Enemy> waveContend100 = new ArrayList<>();
        waveContend100.add(new SuperBoss100Enemy(new Vector(250, -50), gamePath.getSegments()));
        addWave(waveContend100);

        strengthenEnemies();
    }



    private void initLevel1(GamePath gamePath){

//Wave 1
        List<Enemy> waveContend1 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            waveContend1.add(new StandartEnemy(new Vector(250, -550 - 50 * i), gamePath.getSegments()));
        }
//        waveContend1.add(new FastEnemy(new Vector(250, -200), gamePath.getSegments()));
//        waveContend1.add(new FastEnemy(new Vector(250, -300), gamePath.getSegments()));
//        waveContend1.add(new FastEnemy(new Vector(250, -400), gamePath.getSegments()));
//        waveContend1.add(new FastEnemy(new Vector(250, -500), gamePath.getSegments()));
//
//        waveContend1.add(new DistractEnemy(new Vector(250, -300), gamePath.getSegments()));
//        waveContend1.add(new AntiFreezeEnemy(new Vector(250, -400), gamePath.getSegments()));
//        waveContend1.add(new SuperBoss100Enemy(new Vector(250, -500), gamePath.getSegments()));

        addWave(waveContend1);

//Wave 2
        List<Enemy> waveContend2 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend2.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }

        addWave(waveContend2);

//Wave 3
        List<Enemy> waveContend3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waveContend3.add(new StandartEnemy(new Vector(250, -50 - 75 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 2; i++) {
            waveContend3.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        waveContend3.add(new TankEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend3.add(new AntiFreezeEnemy(new Vector(250, -250), gamePath.getSegments()));
        addWave(waveContend3);

//Wave 4
        List<Enemy> waveContend4 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waveContend4.add(new StandartEnemy(new Vector(250, -50 - 60 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend4.add(new FastEnemy(new Vector(250, -800 - 150 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend4.add(new TankEnemy(new Vector(250, -100 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend4.add(new AntiFreezeEnemy(new Vector(250, -250 - 100 * i), gamePath.getSegments()));
        }
        waveContend4.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        addWave(waveContend4);

// Wave 5
        List<Enemy> waveContend5 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            waveContend5.add(new StandartEnemy(new Vector(250, -50 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend5.add(new FastEnemy(new Vector(250, -1400 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend5.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend5.add(new AntiFreezeEnemy(new Vector(250, -75 - 90 * i), gamePath.getSegments()));
        }
        addWave(waveContend5);

// Wave 6
        List<Enemy> waveContend6 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend6.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 7; i++) {
            waveContend6.add(new FastEnemy(new Vector(250, -307 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend6.add(new TankEnemy(new Vector(250, -115 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 7; i++) {
            waveContend6.add(new AntiFreezeEnemy(new Vector(250, -75 - 90 * i), gamePath.getSegments()));
        }
        waveContend6.add(new FastEnemy(new Vector(250, -3000), gamePath.getSegments()));
        addWave(waveContend6);

// Wave 7
        List<Enemy> waveContend7 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend7.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend7.add(new FastEnemy(new Vector(250, -1250 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend7.add(new TankEnemy(new Vector(250, -150 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend7.add(new AntiFreezeEnemy(new Vector(250, -700 - 100 * i), gamePath.getSegments()));
        }
        waveContend7.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        addWave(waveContend7);

// Wave 8
        List<Enemy> waveContend8 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend8.add(new StandartEnemy(new Vector(250, -50 - 60 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend8.add(new FastEnemy(new Vector(250, -1300 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend8.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        waveContend8.add(new DistractEnemy(new Vector(250, -300), gamePath.getSegments()));
        addWave(waveContend8);

// Wave 9
        List<Enemy> waveContend9 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend9.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend9.add(new FastEnemy(new Vector(250, -1300 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 10; i++) {
            waveContend9.add(new TankEnemy(new Vector(250, -123 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend9);

// Wave 10
        List<Enemy> waveContend10 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend10.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend10.add(new FastEnemy(new Vector(250, -300 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend10.add(new TankEnemy(new Vector(250, -150 - 100 * i), gamePath.getSegments()));
        }
        waveContend10.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        addWave(waveContend10);

// Wave 11 (Boss)
        List<Enemy> waveContend11 = new ArrayList<>();
        waveContend11.add(new BossEnemy(new Vector(250, -50), gamePath.getSegments()));
        addWave(waveContend11);

// Wave 12
        List<Enemy> waveContend12 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            waveContend12.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend12.add(new StandartEnemy(new Vector(250, -450 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend12.add(new FastEnemy(new Vector(250, -800 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 2; i++) {
            waveContend12.add(new AntiFreezeEnemy(new Vector(250, -700 - 55 * i), gamePath.getSegments()));
        }
        waveContend12.add(new DistractEnemy(new Vector(250, -300), gamePath.getSegments()));
        addWave(waveContend12);

// Wave 13
        List<Enemy> waveContend13 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            waveContend13.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend13);

// Wave 14
        List<Enemy> waveContend14 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            waveContend14.add(new FastEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend14);

// Wave 15
        List<Enemy> waveContend15 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend15.add(new FastEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
            waveContend15.add(new StandartEnemy(new Vector(250, -200 - 70 * i), gamePath.getSegments()));
            waveContend15.add(new TankEnemy(new Vector(250, -155 - 50 * i), gamePath.getSegments()));
            waveContend15.add(new AntiFreezeEnemy(new Vector(250, -400 - 80 * i), gamePath.getSegments()));
            waveContend15.add(new RegenEnemy(new Vector(250, -33 - 120 * i), gamePath.getSegments()));
            waveContend15.add(new DistractEnemy(new Vector(250, -300 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend15);


// Wave 16
        List<Enemy> waveContend16 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend16.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend16.add(new FastEnemy(new Vector(250, -100 - 100 * i), gamePath.getSegments()));
            waveContend16.add(new DistractEnemy(new Vector(250, -300 - 40 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend16.add(new StandartEnemy(new Vector(250, -350 - 50 * i), gamePath.getSegments()));
            waveContend16.add(new AntiFreezeEnemy(new Vector(250, -100 - 47 * i), gamePath.getSegments()));
        }
        addWave(waveContend16);

// Wave 17
        List<Enemy> waveContend17 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend17.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend17.add(new TankEnemy(new Vector(250, -155 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend17.add(new FastEnemy(new Vector(250, -350 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend17);

// Wave 18
        List<Enemy> waveContend18 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend18.add(new FastEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend18.add(new TankEnemy(new Vector(250, -147 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend18.add(new StandartEnemy(new Vector(250, -350 - 50 * i), gamePath.getSegments()));
        }
        addWave(waveContend18);

// Wave 19
        List<Enemy> waveContend19 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend19.add(new TankEnemy(new Vector(250, -50 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend19.add(new FastEnemy(new Vector(250, -200 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend19.add(new StandartEnemy(new Vector(250, -350 - 50 * i), gamePath.getSegments()));
        }
        addWave(waveContend19);

// Wave 20
        List<Enemy> waveContend20 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            waveContend20.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend20.add(new FastEnemy(new Vector(250, -250 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend20.add(new TankEnemy(new Vector(250, -147 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 2; i++) {
            waveContend20.add(new DistractEnemy(new Vector(250, -250 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend20);
// Wave 21
        List<Enemy> waveContend21 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            waveContend21.add(new BossEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend21);
        // Wave 22
        List<Enemy> waveContend22 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            waveContend22.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend22.add(new FastEnemy(new Vector(250, -300 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend22.add(new TankEnemy(new Vector(250, -1 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend22.add(new AntiFreezeEnemy(new Vector(250, -900 - 90 * i), gamePath.getSegments()));
        }
        addWave(waveContend22);

// Wave 23
        List<Enemy> waveContend23 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waveContend23.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend23.add(new RegenEnemy(new Vector(250, -300 - 75 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend23.add(new AntiFreezeEnemy(new Vector(250, -700 - 25 * i), gamePath.getSegments()));
        }
        addWave(waveContend23);

// Wave 24
        List<Enemy> waveContend24 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            waveContend24.add(new TankEnemy(new Vector(250, -50 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend24.add(new FastEnemy(new Vector(250, -500 - 60 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend24.add(new RegenEnemy(new Vector(250, -1000 - 40 * i), gamePath.getSegments()));
        }
        addWave(waveContend24);

// Wave 25
        List<Enemy> waveContend25 = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            waveContend25.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 6; i++) {
            waveContend25.add(new AntiFreezeEnemy(new Vector(250, -400 - 90 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend25.add(new RegenEnemy(new Vector(250, -800 - 60 * i), gamePath.getSegments()));
        }
        addWave(waveContend25);

// Wave 26
        List<Enemy> waveContend26 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waveContend26.add(new FastEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend26.add(new AntiFreezeEnemy(new Vector(250, -500 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend26.add(new RegenEnemy(new Vector(250, -1000 - 20 * i), gamePath.getSegments()));
        }
        addWave(waveContend26);

// Wave 27
        List<Enemy> waveContend27 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            waveContend27.add(new TankEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 6; i++) {
            waveContend27.add(new AntiFreezeEnemy(new Vector(250, -500 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend27);

// Wave 28
        List<Enemy> waveContend28 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            waveContend28.add(new RegenEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend28.add(new FastEnemy(new Vector(250, -1300 - 60 * i), gamePath.getSegments()));
        }
        addWave(waveContend28);

// Wave 29
        List<Enemy> waveContend29 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend29.add(new BossEnemy(new Vector(250, -50 - 300 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend29.add(new AntiFreezeEnemy(new Vector(250, -1000 - 70 * i), gamePath.getSegments()));
        }
        addWave(waveContend29);

// Wave 30 (Final Boss Wave)
        List<Enemy> waveContend30 = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            waveContend30.add(new BossEnemy(new Vector(250, -50 - 300 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 2; i++) {
            waveContend30.add(new RegenEnemy(new Vector(250, -200 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend30.add(new AntiFreezeEnemy(new Vector(250, -400 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend30);
    }

    public void initLevel2(GamePath gamePath){

        // Wave 1
        List<Enemy> waveContend1 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            waveContend1.add(new StandartEnemy(new Vector(250, -500 - 60 * i), gamePath.getSegments()));
        }
        addWave(waveContend1);

        // Wave 2
        List<Enemy> waveContend2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            waveContend2.add(new StandartEnemy(new Vector(250, -50 - 55 * i), gamePath.getSegments()));
        }
        addWave(waveContend2);

        List<Enemy> waveContend3 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            waveContend3.add(new StandartEnemy(new Vector(250, -50 - 75 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend3.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        waveContend3.add(new TankEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend3.add(new AntiFreezeEnemy(new Vector(250, -250), gamePath.getSegments()));
        addWave(waveContend3);

        // Wave 4
        List<Enemy> waveContend4 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend4.add(new StandartEnemy(new Vector(250, -50 - 60 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend4.add(new FastEnemy(new Vector(250, -800 - 150 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend4.add(new TankEnemy(new Vector(250, -17 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 2; i++) {
            waveContend4.add(new AntiFreezeEnemy(new Vector(250, -250 - 100 * i), gamePath.getSegments()));
        }
        waveContend4.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        addWave(waveContend4);

        // Wave 5
        List<Enemy> waveContend5 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend5.add(new StandartEnemy(new Vector(250, -50 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 7; i++) {
            waveContend5.add(new FastEnemy(new Vector(250, -1400 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 7; i++) {
            waveContend5.add(new TankEnemy(new Vector(250, -50 - 150 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend5.add(new RegenEnemy(new Vector(250, -75 - 90 * i), gamePath.getSegments()));
        }
        addWave(waveContend5);

        // Wave 6
        List<Enemy> waveContend6 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend6.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 6; i++) {
            waveContend6.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 10; i++) {
            waveContend6.add(new TankEnemy(new Vector(250, -200 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend6.add(new AntiFreezeEnemy(new Vector(250, -75 - 90 * i), gamePath.getSegments()));
        }
        waveContend6.add(new DistractEnemy(new Vector(250, -300), gamePath.getSegments()));
        addWave(waveContend6);

        // Wave 7
        List<Enemy> waveContend7 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend7.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 7; i++) {
            waveContend7.add(new FastEnemy(new Vector(250, -1250 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 9; i++) {
            waveContend7.add(new TankEnemy(new Vector(250, -124 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend7.add(new RegenEnemy(new Vector(250, -700 - 120 * i), gamePath.getSegments()));
        }
        waveContend7.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        addWave(waveContend7);

        // Wave 8
        List<Enemy> waveContend8 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            waveContend8.add(new StandartEnemy(new Vector(250, -50 - 60 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 10; i++) {
            waveContend8.add(new FastEnemy(new Vector(250, -1300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 12; i++) {
            waveContend8.add(new TankEnemy(new Vector(250, -150 - 150 * i), gamePath.getSegments()));
        }
        waveContend8.add(new DistractEnemy(new Vector(250, -300), gamePath.getSegments()));
        addWave(waveContend8);

        // Wave 9
        List<Enemy> waveContend9 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend9.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 7; i++) {
            waveContend9.add(new FastEnemy(new Vector(250, -1000 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend9.add(new TankEnemy(new Vector(250, -77 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend9);

        // Wave 10
        List<Enemy> waveContend10 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend10.add(new RegenEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 7; i++) {
            waveContend10.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend10.add(new TankEnemy(new Vector(250, -100 - 100 * i), gamePath.getSegments()));
        }
        waveContend10.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        addWave(waveContend10);

        // Wave 11 (Boss)
        List<Enemy> waveContend11 = new ArrayList<>();
        waveContend11.add(new BossEnemy(new Vector(250, -50), gamePath.getSegments()));
        addWave(waveContend11);

        // Wave 12
        List<Enemy> waveContend12 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            waveContend12.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend12.add(new RegenEnemy(new Vector(250, -450 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend12.add(new FastEnemy(new Vector(250, -800 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 6; i++) {
            waveContend12.add(new AntiFreezeEnemy(new Vector(250, -700 - 55 * i), gamePath.getSegments()));
        }
        waveContend12.add(new DistractEnemy(new Vector(250, -300), gamePath.getSegments()));
        addWave(waveContend12);

        // Wave 13
        List<Enemy> waveContend13 = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            waveContend13.add(new TankEnemy(new Vector(250, -100 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 13; i++) {
            waveContend13.add(new FastEnemy(new Vector(250, -750 - 150 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend13.add(new RegenEnemy(new Vector(250, -500 - 55 * i), gamePath.getSegments()));
        }
        waveContend13.add(new DistractEnemy(new Vector(250, -300 - 50), gamePath.getSegments()));
        addWave(waveContend13);

        for (int i = 14; i <= 30; i++) {
            List<Enemy> waveContend = new ArrayList<>();
            for (int j = 0; j < i + 6; j++) {
                waveContend.add(new StandartEnemy(new Vector(250, -50 - 50 * j), gamePath.getSegments()));
            }
            for (int j = 0; j < i + 4; j++) {
                waveContend.add(new FastEnemy(new Vector(250, -100 - 100 * j), gamePath.getSegments()));
            }
            for (int j = 0; j < i + 3; j++) {
                waveContend.add(new TankEnemy(new Vector(250, -22 - 50 * j), gamePath.getSegments()));
            }
            for (int j = 0; j < i + 2; j++) {
                waveContend.add(new AntiFreezeEnemy(new Vector(250, -100 - 90 * j), gamePath.getSegments()));
            }
            if (i % 5 == 0) {
                waveContend.add(new BossEnemy(new Vector(250, -70), gamePath.getSegments()));
            }
            if (i % 3 == 0) {
                waveContend.add(new DistractEnemy(new Vector(250, -50), gamePath.getSegments()));
            }
            addWave(waveContend);
        }
    }

    public void initLevel3(GamePath gamePath){

        // Wave 1
        List<Enemy> waveContend1 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            waveContend1.add(new StandartEnemy(new Vector(250, -500 - 60 * i), gamePath.getSegments()));
        }
        addWave(waveContend1);

        // Wave 2
        List<Enemy> waveContend2 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            waveContend2.add(new StandartEnemy(new Vector(250, -50 - 55 * i), gamePath.getSegments()));
        }
        addWave(waveContend2);

        List<Enemy> waveContend3 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend3.add(new StandartEnemy(new Vector(250, -50 - 75 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend3.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        waveContend3.add(new TankEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend3.add(new RegenEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend3.add(new AntiFreezeEnemy(new Vector(250, -250), gamePath.getSegments()));
        addWave(waveContend3);


// Wave 4
        List<Enemy> waveContend4 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            waveContend4.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend4.add(new RegenEnemy(new Vector(250, -120 - 120 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend4.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        addWave(waveContend4);

// Wave 5
        List<Enemy> waveContend5 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waveContend5.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 6; i++) {
            waveContend5.add(new TankEnemy(new Vector(250, -200 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend5.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend5.add(new FastEnemy(new Vector(250, -150 - 500 * i), gamePath.getSegments()));
        }
        addWave(waveContend5);

// Wave 6 (continued)
        List<Enemy> waveContend6 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            waveContend6.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend6.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend6.add(new TankEnemy(new Vector(250, -20 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend6.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend6.add(new AntiFreezeEnemy(new Vector(250, -150 - 90 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend6.add(new DistractEnemy(new Vector(250, -150 - 200 * i), gamePath.getSegments()));
        }
        addWave(waveContend6);

// Wave 7
        List<Enemy> waveContend7 = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            waveContend7.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend7.add(new AntiFreezeEnemy(new Vector(250, -700 - 120 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend7.add(new TankEnemy(new Vector(250, -47 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend7.add(new RegenEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend7.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }

        addWave(waveContend7);

// Wave 8
        List<Enemy> waveContend8 = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            waveContend8.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 1; i++) {
            waveContend8.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend8.add(new TankEnemy(new Vector(250, -112 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend8.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        addWave(waveContend8);

// Wave 9
        List<Enemy> waveContend9 = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            waveContend9.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 12; i++) {
            waveContend9.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend9.add(new TankEnemy(new Vector(250, -24 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend9.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend9);

// Wave 10
        List<Enemy> waveContend10 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend10.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 11; i++) {
            waveContend10.add(new TankEnemy(new Vector(250, -18 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend10.add(new RegenEnemy(new Vector(250, -500 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend10.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        waveContend10.add(new BossEnemy(new Vector(250, -50), gamePath.getSegments()));
        addWave(waveContend10);

// Wave 11
        List<Enemy> waveContend11 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            waveContend11.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 14; i++) {
            waveContend11.add(new AntiFreezeEnemy(new Vector(250, -700 - 120 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend11.add(new TankEnemy(new Vector(250, -66 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend11.add(new FastEnemy(new Vector(250, -150 - 200 * i), gamePath.getSegments()));
        }
        addWave(waveContend11);

// Wave 12
        List<Enemy> waveContend12 = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            waveContend12.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 22; i++) {
            waveContend12.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend6.add(new TankEnemy(new Vector(250, -69 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend6.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend12);

// Wave 13
        List<Enemy> waveContend13 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend13.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 19; i++) {
            waveContend13.add(new AntiFreezeEnemy(new Vector(250, -700 - 120 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend13.add(new TankEnemy(new Vector(250, -55 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend13.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        addWave(waveContend13);

// Wave 14
        List<Enemy> waveContend14 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend14.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend14.add(new DistractEnemy(new Vector(250, -300 - 100), gamePath.getSegments()));
        }
        for (int i = 0; i < 42; i++) {
            waveContend14.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend14.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend14);

// Wave 15
        List<Enemy> waveContend15 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend15.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 15; i++) {
            waveContend15.add(new TankEnemy(new Vector(250, -12 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend15.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend15.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend15);

// Wave 16
        List<Enemy> waveContend16 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend16.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 22; i++) {
            waveContend16.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend16.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend16);

// Wave 17
        List<Enemy> waveContend17 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend17.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 10; i++) {
            waveContend17.add(new AntiFreezeEnemy(new Vector(250, -400 - 120 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend17.add(new TankEnemy(new Vector(250, -2 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend17.add(new FastEnemy(new Vector(250, -140 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend17);

// Wave 18
        List<Enemy> waveContend18 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend18.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend18.add(new DistractEnemy(new Vector(250, -300 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 15; i++) {
            waveContend18.add(new TankEnemy(new Vector(250, -7 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend18);

// Wave 19
        List<Enemy> waveContend19 = new ArrayList<>();
        for (int i = 0; i < 19; i++) {
            waveContend19.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 22; i++) {
            waveContend19.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend19.add(new TankEnemy(new Vector(250, -100 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend19);

// Wave 20
        List<Enemy> waveContend20 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            waveContend20.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 15; i++) {
            waveContend20.add(new TankEnemy(new Vector(250, -100 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend20.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        waveContend20.add(new BossEnemy(new Vector(250, -50), gamePath.getSegments()));
        addWave(waveContend20);

// Wave 21
        List<Enemy> waveContend21 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            waveContend21.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 15; i++) {
            waveContend21.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend21.add(new TankEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        addWave(waveContend21);

// Wave 22
        List<Enemy> waveContend22 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            waveContend22.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 10; i++) {
            waveContend22.add(new AntiFreezeEnemy(new Vector(250, -200 - 120 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 6; i++) {
            waveContend22.add(new TankEnemy(new Vector(250, -100 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend22.add(new FastEnemy(new Vector(250, -1450 - 200 * i), gamePath.getSegments()));
        }
        addWave(waveContend22);

// Wave 23 (continued)
        List<Enemy> waveContend23 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            waveContend23.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 25; i++) {
            waveContend23.add(new FastEnemy(new Vector(250, -300 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend23.add(new RegenEnemy(new Vector(250, -150 - 80 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend23.add(new TankEnemy(new Vector(250, -150 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend23.add(new DistractEnemy(new Vector(250, -150 - 50 * i), gamePath.getSegments()));
        }
        addWave(waveContend23);

// Wave 24
        List<Enemy> waveContend24 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            waveContend24.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 20; i++) {
            waveContend24.add(new TankEnemy(new Vector(250, -27 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 8; i++) {
            waveContend24.add(new RegenEnemy(new Vector(250, -150 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 15; i++) {
            waveContend24.add(new FastEnemy(new Vector(250, -150 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 5; i++) {
            waveContend24.add(new DistractEnemy(new Vector(250, -150 - 90 * i), gamePath.getSegments()));
        }
        addWave(waveContend24);

// Wave 25
        List<Enemy> waveContend25 = new ArrayList<>();
        for (int i = 0; i < 25; i++) {
            waveContend25.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 13; i++) {
            waveContend25.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
            waveContend25.add(new RegenEnemy(new Vector(250, -300 - 80 * i), gamePath.getSegments()));
            waveContend25.add(new TankEnemy(new Vector(250, -100 - 86 * i), gamePath.getSegments()));
        }
        waveContend25.add(new BossEnemy(new Vector(250, -50), gamePath.getSegments()));
        addWave(waveContend25);

// Wave 26
        List<Enemy> waveContend26 = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            waveContend26.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 25; i++) {
            waveContend26.add(new AntiFreezeEnemy(new Vector(250, -700 - 120 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 15; i++) {
            waveContend26.add(new TankEnemy(new Vector(250, -100 - 88 * i), gamePath.getSegments()));
            waveContend26.add(new RegenEnemy(new Vector(250, -100 - 75 * i), gamePath.getSegments()));
        }
        addWave(waveContend26);

// Wave 27
        List<Enemy> waveContend27 = new ArrayList<>();
        for (int i = 0; i < 27; i++) {
            waveContend27.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend27.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
            waveContend27.add(new BossEnemy(new Vector(250, -50 - 120 * i), gamePath.getSegments()));
        }
        addWave(waveContend27);

// Wave 28
        List<Enemy> waveContend28 = new ArrayList<>();
        for (int i = 0; i < 28; i++) {
            waveContend28.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 32; i++) {
            waveContend28.add(new TankEnemy(new Vector(250, -50 - 65 * i), gamePath.getSegments()));
        }
        addWave(waveContend28);

// Wave 29
        List<Enemy> waveContend29 = new ArrayList<>();
        for (int i = 0; i < 29; i++) {
            waveContend29.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 12; i++) {
            waveContend29.add(new FastEnemy(new Vector(250, -177 - 80 * i), gamePath.getSegments()));
            waveContend29.add(new RegenEnemy(new Vector(250, -111 - 75 * i), gamePath.getSegments()));
            waveContend29.add(new DistractEnemy(new Vector(250, -144 - 70 * i), gamePath.getSegments()));
            waveContend29.add(new AntiFreezeEnemy(new Vector(250, -122 - 65 * i), gamePath.getSegments()));
        }
        addWave(waveContend29);

// Wave 30
        List<Enemy> waveContend30 = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            waveContend30.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 22; i++) {
            waveContend30.add(new AntiFreezeEnemy(new Vector(250, -700 - 120 * i), gamePath.getSegments()));
        }
        waveContend30.add(new SuperBoss100Enemy(new Vector(250, -600), gamePath.getSegments()));
        addWave(waveContend30);

    }
    public void createRandom(GamePath gamePath){
        for (int i = waveList.size(); i < 100; i++) {
            List waveContend = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                Random rand = new Random();
                int randomEnemy = rand.nextInt(13);
                switch (randomEnemy){
                    case 0:
                    case 1:
                        waveContend.add(new StandartEnemy(new Vector(250,-50-100*j/3),gamePath.getSegments()));
                        break;
                    case 2:
                    case 3:
                        waveContend.add(new FastEnemy(new Vector(250,-504-75*j/3),gamePath.getSegments()));
                        break;
                    case 4:
                    case 5:
                        waveContend.add(new TankEnemy(new Vector(250,-56-80*j/3),gamePath.getSegments()));
                        break;
                    case 6:
                    case 7:
                        waveContend.add(new AntiFreezeEnemy(new Vector(250,-100-80*j/3),gamePath.getSegments()));
                        break;
                    case 8:
                    case 9:
                        waveContend.add(new RegenEnemy(new Vector(250,-100-80*j/3),gamePath.getSegments()));
                        break;
                    case 10:
                        waveContend.add(new BossEnemy(new Vector(250,-100-80*j/3),gamePath.getSegments()));
                        break;
                    case 11:
                    case 12:
                        waveContend.add(new DistractEnemy(new Vector(250, -170-80*j/3), gamePath.getSegments()));
                        break;
                }
            }
            addWave(waveContend);
        }
    }

    public int getMaxWave() {
        return maxWave;
    }

    public void setMaxWave(int maxWave) {
        this.maxWave = maxWave;
    }
}