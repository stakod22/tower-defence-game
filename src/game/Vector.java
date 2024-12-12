package game;

public class Vector {
    public int x;
    public int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void add(Vector other){
        this.x += other.x;
        this.y += other.y;
    }

    public void multi(Vector other){
        this.x *= other.x;
        this.y *= other.y;
    }

    public int distanceToOther(Vector other){
        if (other == null){
            return -1;
        }
        int distance = (int) Math.sqrt((double)(this.x-other.x)+(double)(this.y-other.y));
        return distance;
    }
}