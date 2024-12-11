package game;

import java.util.Vector;

public abstract class PlayMap implements Drawable{
    private Vector[] path;


    public PlayMap(Vector[] path) {
        this.path = path;
    }

    public Vector[] getPath() {
        return path;
    }
}
