package game.wave;

import game.path.GamePath;
import game.framework.Vector;
import game.enemies.*;

import java.util.ArrayList;
import java.util.List;

public class WaveList {
    private List<Wave> waveList = new ArrayList<>();
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

    public List<Wave> getWaveList() {
        return waveList;
    }

    public void setWaveList(List<Wave> waveList) {
        this.waveList = waveList;
    }
    public void currentWaveDone(){
        currentWave++;
    };

    public int getCurrentWave() {
        return currentWave;
    }

    public void showAllWaves() {
        for (Wave wave : waveList) {
            System.out.println(wave.toString());
        }
    }
    public void strengthenEnemies(){
        for (int i = 0; i < waveList.size(); i++) {
            float healthincrease = 1.1f;
            float speedincrease = 1.05f;

            for (Enemy e : waveList.get(i).enemies){
                e.setHealth((int) (e.getHealth()*(Math.pow((double) healthincrease,(double) i))));
                e.setRealSpeedValue((int) (e.getSpeedValue()*(Math.pow((double) speedincrease,(double) i))));
            }
            //System.out.println("Boss Health in Round:"+(i+1)+" : "+50*Math.pow((double) healthincrease,(double) i));
            //System.out.println("Standard Speed in Round:"+(i+1)+" : "+Math.pow((double) healthincrease,(double) i));

        }
    }




    public void init(GamePath gamePath){
//Wave 1
        List<Enemy> waveContend1 = new ArrayList<>();
        waveContend1.add(new StandartEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend1.add(new StandartEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend1.add(new StandartEnemy(new Vector(250, -300), gamePath.getSegments()));
        addWave(waveContend1);

//Wave 2
        List<Enemy> waveContend2 = new ArrayList<>();
        waveContend2.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend2.add(new StandartEnemy(new Vector(250, -100), gamePath.getSegments()));
        waveContend2.add(new StandartEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend2.add(new StandartEnemy(new Vector(250, -300), gamePath.getSegments()));
        waveContend2.add(new StandartEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend2.add(new StandartEnemy(new Vector(250, -450), gamePath.getSegments()));

        addWave(waveContend2);

//Wave 3
        List<Enemy> waveContend3 = new ArrayList<>();
        waveContend3.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend3.add(new StandartEnemy(new Vector(250, -100), gamePath.getSegments()));
        waveContend3.add(new StandartEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend3.add(new FastEnemy(new Vector(250, -1450), gamePath.getSegments()));
        waveContend3.add(new FastEnemy(new Vector(250, -1650), gamePath.getSegments()));
        waveContend2.add(new FastEnemy(new Vector(250, -3000), gamePath.getSegments()));
        waveContend3.add(new StandartEnemy(new Vector(250, -300), gamePath.getSegments()));
        waveContend3.add(new StandartEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend3.add(new TankEnemy(new Vector(250, -250), gamePath.getSegments()));
        addWave(waveContend3);

//Wave 4
        List<Enemy> waveContend4 = new ArrayList<>();
        waveContend4.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend4.add(new StandartEnemy(new Vector(250, -110), gamePath.getSegments()));
        waveContend4.add(new StandartEnemy(new Vector(250, -180), gamePath.getSegments()));
        waveContend4.add(new FastEnemy(new Vector(250, -1400), gamePath.getSegments()));
        waveContend4.add(new FastEnemy(new Vector(250, -600), gamePath.getSegments()));
        waveContend4.add(new TankEnemy(new Vector(250, -300), gamePath.getSegments()));
        waveContend4.add(new TankEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend4.add(new FastEnemy(new Vector(250, -1100), gamePath.getSegments()));
        addWave(waveContend4);

//Wave 5
        List<Enemy> waveContend5 = new ArrayList<>();
        waveContend5.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend5.add(new StandartEnemy(new Vector(250, -120), gamePath.getSegments()));
        waveContend5.add(new StandartEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend5.add(new FastEnemy(new Vector(250, -1400), gamePath.getSegments()));
        waveContend5.add(new FastEnemy(new Vector(250, -1600), gamePath.getSegments()));
        waveContend5.add(new FastEnemy(new Vector(250, -1800), gamePath.getSegments()));
        waveContend5.add(new TankEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend5.add(new TankEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend5.add(new TankEnemy(new Vector(250, -200), gamePath.getSegments()));
        addWave(waveContend5);

//Wave 6
        List<Enemy> waveContend6 = new ArrayList<>();
        waveContend6.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend6.add(new StandartEnemy(new Vector(250, -100), gamePath.getSegments()));
        waveContend6.add(new StandartEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend6.add(new FastEnemy(new Vector(250, -300), gamePath.getSegments()));
        waveContend6.add(new FastEnemy(new Vector(250, -500), gamePath.getSegments()));
        waveContend6.add(new TankEnemy(new Vector(250, -600), gamePath.getSegments()));
        waveContend6.add(new TankEnemy(new Vector(250, -500), gamePath.getSegments()));
        waveContend6.add(new TankEnemy(new Vector(250, -300), gamePath.getSegments()));
        waveContend6.add(new FastEnemy(new Vector(250, -3000), gamePath.getSegments()));
        addWave(waveContend6);

//Wave 7
        List<Enemy> waveContend7 = new ArrayList<>();
        waveContend7.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend7.add(new StandartEnemy(new Vector(250, -100), gamePath.getSegments()));
        waveContend7.add(new StandartEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend7.add(new FastEnemy(new Vector(250, -1250), gamePath.getSegments()));
        waveContend7.add(new FastEnemy(new Vector(250, -1450), gamePath.getSegments()));
        waveContend7.add(new FastEnemy(new Vector(250, -5650), gamePath.getSegments()));
        waveContend7.add(new TankEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend7.add(new TankEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend7.add(new TankEnemy(new Vector(250, -450), gamePath.getSegments()));
        addWave(waveContend7);

//Wave 8
        List<Enemy> waveContend8 = new ArrayList<>();
        waveContend8.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend8.add(new StandartEnemy(new Vector(250, -110), gamePath.getSegments()));
        waveContend8.add(new FastEnemy(new Vector(250, -1300), gamePath.getSegments()));
        waveContend8.add(new FastEnemy(new Vector(250, -1500), gamePath.getSegments()));
        waveContend8.add(new FastEnemy(new Vector(250, -1700), gamePath.getSegments()));
        waveContend8.add(new TankEnemy(new Vector(250, -250), gamePath.getSegments()));
        waveContend8.add(new TankEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend8.add(new TankEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend8.add(new TankEnemy(new Vector(250, -800), gamePath.getSegments()));
        addWave(waveContend8);

//Wave 9
        List<Enemy> waveContend9 = new ArrayList<>();
        waveContend9.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend9.add(new StandartEnemy(new Vector(250, -100), gamePath.getSegments()));
        waveContend9.add(new StandartEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend9.add(new FastEnemy(new Vector(250, -1300), gamePath.getSegments()));
        waveContend9.add(new FastEnemy(new Vector(250, -4150), gamePath.getSegments()));
        waveContend9.add(new FastEnemy(new Vector(250, -6010), gamePath.getSegments()));
        waveContend9.add(new TankEnemy(new Vector(250, -400), gamePath.getSegments()));
        waveContend9.add(new TankEnemy(new Vector(250, -500), gamePath.getSegments()));
        waveContend9.add(new TankEnemy(new Vector(250, -600), gamePath.getSegments()));
        waveContend9.add(new TankEnemy(new Vector(250, -700), gamePath.getSegments()));
        addWave(waveContend9);

//Wave 10
        List<Enemy> waveContend10 = new ArrayList<>();
        waveContend10.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend10.add(new StandartEnemy(new Vector(250, -100), gamePath.getSegments()));
        waveContend10.add(new StandartEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend10.add(new FastEnemy(new Vector(250, -300), gamePath.getSegments()));
        waveContend10.add(new FastEnemy(new Vector(250, -500), gamePath.getSegments()));
        waveContend10.add(new FastEnemy(new Vector(250, -700), gamePath.getSegments()));
        waveContend10.add(new TankEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend10.add(new TankEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend10.add(new TankEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend10.add(new TankEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend10.add(new TankEnemy(new Vector(250, -350), gamePath.getSegments()));
        addWave(waveContend10);



//Wave 11 (Boss)
        List<Enemy> waveContend11 = new ArrayList<>();
        waveContend11.add(new BossEnemy(new Vector(250, -50), gamePath.getSegments()));
        addWave(waveContend11);

//Wave 12
        List<Enemy> waveContend12 = new ArrayList<>();
        waveContend12.add(new TankEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend12.add(new TankEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend12.add(new TankEnemy(new Vector(250, -250), gamePath.getSegments()));
        waveContend12.add(new TankEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend12.add(new StandartEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend12.add(new StandartEnemy(new Vector(250, -500), gamePath.getSegments()));
        waveContend12.add(new StandartEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend12.add(new StandartEnemy(new Vector(250, -600), gamePath.getSegments()));
        waveContend12.add(new FastEnemy(new Vector(250, -800), gamePath.getSegments()));
        waveContend12.add(new FastEnemy(new Vector(250, -900), gamePath.getSegments()));
        waveContend12.add(new FastEnemy(new Vector(250, -1000), gamePath.getSegments()));
        waveContend12.add(new FastEnemy(new Vector(250, -1100), gamePath.getSegments()));
        addWave(waveContend12);
//Wave 13
        List<Enemy> waveContend13 = new ArrayList<>();
        waveContend13.add(new TankEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -100), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -150), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -250), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend13.add(new TankEnemy(new Vector(250, -1050), gamePath.getSegments()));
        addWave(waveContend13);
        strengthenEnemies();
//Wave 14
        List<Enemy> waveContend14 = new ArrayList<>();
        waveContend14.add(new FastEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -1050), gamePath.getSegments()));
        waveContend14.add(new FastEnemy(new Vector(250, -1150), gamePath.getSegments()));
        addWave(waveContend14);

//Wave 15
        List<Enemy> waveContend15 = new ArrayList<>();
        waveContend15.add(new FastEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend15.add(new StandartEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend15.add(new TankEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend15.add(new FastEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend15.add(new StandartEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend15.add(new TankEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend15.add(new FastEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend15.add(new StandartEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend15.add(new FastEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend15.add(new TankEnemy(new Vector(250, -1050), gamePath.getSegments()));
        addWave(waveContend15);

//Wave 16
        List<Enemy> waveContend16 = new ArrayList<>();
        waveContend16.add(new TankEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend16.add(new FastEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend16.add(new StandartEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend16.add(new FastEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend16.add(new TankEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend16.add(new StandartEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend16.add(new FastEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend16.add(new TankEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend16.add(new StandartEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend16.add(new FastEnemy(new Vector(250, -1050), gamePath.getSegments()));
        addWave(waveContend16);

//Wave 17
        List<Enemy> waveContend17 = new ArrayList<>();
        waveContend17.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend17.add(new TankEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend17.add(new FastEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend17.add(new TankEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend17.add(new StandartEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend17.add(new FastEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend17.add(new TankEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend17.add(new FastEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend17.add(new StandartEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend17.add(new TankEnemy(new Vector(250, -1050), gamePath.getSegments()));
        addWave(waveContend17);

//Wave 18
        List<Enemy> waveContend18 = new ArrayList<>();
        waveContend18.add(new FastEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend18.add(new TankEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend18.add(new StandartEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend18.add(new TankEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend18.add(new FastEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend18.add(new StandartEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend18.add(new TankEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend18.add(new FastEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend18.add(new StandartEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend18.add(new TankEnemy(new Vector(250, -1050), gamePath.getSegments()));
        addWave(waveContend18);

//Wave 19
        List<Enemy> waveContend19 = new ArrayList<>();
        waveContend19.add(new TankEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend19.add(new FastEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend19.add(new StandartEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend19.add(new TankEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend19.add(new FastEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend19.add(new StandartEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend19.add(new TankEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend19.add(new FastEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend19.add(new StandartEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend19.add(new TankEnemy(new Vector(250, -1050), gamePath.getSegments()));
        addWave(waveContend19);

//Wave 20
        List<Enemy> waveContend20 = new ArrayList<>();
        waveContend20.add(new StandartEnemy(new Vector(250, -50), gamePath.getSegments()));
        waveContend20.add(new FastEnemy(new Vector(250, -200), gamePath.getSegments()));
        waveContend20.add(new TankEnemy(new Vector(250, -350), gamePath.getSegments()));
        waveContend20.add(new FastEnemy(new Vector(250, -450), gamePath.getSegments()));
        waveContend20.add(new StandartEnemy(new Vector(250, -550), gamePath.getSegments()));
        waveContend20.add(new TankEnemy(new Vector(250, -650), gamePath.getSegments()));
        waveContend20.add(new FastEnemy(new Vector(250, -750), gamePath.getSegments()));
        waveContend20.add(new TankEnemy(new Vector(250, -850), gamePath.getSegments()));
        waveContend20.add(new StandartEnemy(new Vector(250, -950), gamePath.getSegments()));
        waveContend20.add(new FastEnemy(new Vector(250, -1050), gamePath.getSegments()));
        addWave(waveContend20);

        strengthenEnemies();
    }
}
