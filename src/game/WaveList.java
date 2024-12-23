package game;

import java.util.ArrayList;
import java.util.List;

public class WaveList {
    private List<Wave> waveList = new ArrayList<>();

    public void addWave(List<Enemy> e){
        waveList.add(new Wave(e));
    }

    public List<Wave> getWaveList() {
        return waveList;
    }

    public void setWaveList(List<Wave> waveList) {
        this.waveList = waveList;
    }
}
