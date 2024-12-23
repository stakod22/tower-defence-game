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
        int distX = this.x - other.x;
        int distY = this.y - other.y;
        if (distY < 0){
            distY *= -1;
        }
        if (distX <0){
            distX *= -1;
        }
        int distance = (int) Math.sqrt(Math.pow((double) distX,2)+Math.pow((double) distY,2));
        return distance;
    }
}