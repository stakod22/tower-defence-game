package game;

import java.util.ArrayList;
import java.util.List;

public class WaveList {
    private List<Wave> waveList = new ArrayList<>();
    private int currentWave = 0;

    public void addWave(List<Enemy> e){
        waveList.add(new Wave(e));
    }
    public Wave getNextWave(){
        return waveList.get(currentWave);
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
}
