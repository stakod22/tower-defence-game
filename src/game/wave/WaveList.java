package game.wave;

import game.path.GamePath;
import game.framework.Vector;
import game.enemies.*;

import java.util.ArrayList;
import java.util.List;

public class WaveList {
    private final List<Wave> waveList = new ArrayList<>();
    private int currentWave = 0;

    public void addWave(List<Enemy> e){
        waveList.add(new Wave(e));
    }

    public Wave getNextWave(){
        if (currentWave < waveList.size()){
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
            float healthincrease = 1.1f;
            float speedincrease = 1.05f;

            for (Enemy e : waveList.get(i).enemies){
                e.setHealth((int) (e.getHealth()*(Math.pow(healthincrease,i))));
                e.setRealSpeedValue((int) (e.getSpeedValue()*(Math.pow(speedincrease,i))));
            }
        }
    }




    public void init(GamePath gamePath){
//Wave 1
        List<Enemy> waveContend1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend1.add(new StandartEnemy(new Vector(250, -50-75*i), gamePath.getSegments()));
        }
        addWave(waveContend1);

//Wave 2
        List<Enemy> waveContend2 = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            waveContend2.add(new StandartEnemy(new Vector(250, -50-50*i), gamePath.getSegments()));
        }

        addWave(waveContend2);

//Wave 3
        List<Enemy> waveContend3 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            waveContend3.add(new StandartEnemy(new Vector(250, -50-75*i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend3.add(new FastEnemy(new Vector(250, -1450-200*i), gamePath.getSegments()));
        }
        waveContend3.add(new TankEnemy(new Vector(250, -250), gamePath.getSegments()));
        addWave(waveContend3);

//Wave 4
        List<Enemy> waveContend4 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend4.add(new StandartEnemy(new Vector(250, -50-60*i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend4.add(new FastEnemy(new Vector(250, -800-150*i), gamePath.getSegments()));
        }
        for (int i = 0; i < 2; i++) {
            waveContend4.add(new TankEnemy(new Vector(250, -300-100*i), gamePath.getSegments()));
        }
        addWave(waveContend4);

// Wave 5
        List<Enemy> waveContend5 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend5.add(new StandartEnemy(new Vector(250, -50 - 70 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend5.add(new FastEnemy(new Vector(250, -1400 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend5.add(new TankEnemy(new Vector(250, -50 - 150 * i), gamePath.getSegments()));
        }
        addWave(waveContend5);

// Wave 6
        List<Enemy> waveContend6 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend6.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 2; i++) {
            waveContend6.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend6.add(new TankEnemy(new Vector(250, -500 - 100 * i), gamePath.getSegments()));
        }
        waveContend6.add(new FastEnemy(new Vector(250, -3000), gamePath.getSegments()));
        addWave(waveContend6);

// Wave 7
        List<Enemy> waveContend7 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend7.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend7.add(new FastEnemy(new Vector(250, -1250 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend7.add(new TankEnemy(new Vector(250, -450 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend7);

// Wave 8
        List<Enemy> waveContend8 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend8.add(new StandartEnemy(new Vector(250, -50 - 60 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend8.add(new FastEnemy(new Vector(250, -1300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend8.add(new TankEnemy(new Vector(250, -250 - 150 * i), gamePath.getSegments()));
        }
        addWave(waveContend8);

// Wave 9
        List<Enemy> waveContend9 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend9.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend9.add(new FastEnemy(new Vector(250, -1300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend9.add(new TankEnemy(new Vector(250, -400 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend9);

// Wave 10
        List<Enemy> waveContend10 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend10.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend10.add(new FastEnemy(new Vector(250, -300 - 200 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend10.add(new TankEnemy(new Vector(250, -850 - 100 * i), gamePath.getSegments()));
        }
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
            waveContend15.add(new StandartEnemy(new Vector(250, -200 - 100 * i), gamePath.getSegments()));
            waveContend15.add(new TankEnemy(new Vector(250, -350 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend15);


// Wave 16
        List<Enemy> waveContend16 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend16.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend16.add(new FastEnemy(new Vector(250, -200 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 4; i++) {
            waveContend16.add(new StandartEnemy(new Vector(250, -350 - 50 * i), gamePath.getSegments()));
        }
        addWave(waveContend16);

// Wave 17
        List<Enemy> waveContend17 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend17.add(new StandartEnemy(new Vector(250, -50 - 50 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend17.add(new TankEnemy(new Vector(250, -200 - 100 * i), gamePath.getSegments()));
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
            waveContend18.add(new TankEnemy(new Vector(250, -200 - 100 * i), gamePath.getSegments()));
        }
        for (int i = 0; i < 3; i++) {
            waveContend18.add(new StandartEnemy(new Vector(250, -350 - 50 * i), gamePath.getSegments()));
        }
        addWave(waveContend18);

// Wave 19
        List<Enemy> waveContend19 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            waveContend19.add(new TankEnemy(new Vector(250, -50 - 100 * i), gamePath.getSegments()));
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
            waveContend20.add(new TankEnemy(new Vector(250, -450 - 100 * i), gamePath.getSegments()));
        }
        addWave(waveContend20);

        strengthenEnemies();
    }
}
