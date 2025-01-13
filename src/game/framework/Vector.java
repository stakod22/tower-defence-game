package game.framework;

public class Vector {
    public int x;
    public int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector(Vector v) {
        this.x = v.x;
        this.y = v.y;
    }

    public void add(Vector other){
        this.x += other.x;
        this.y += other.y;
    }

    public void add(int adder){
        this.x += adder;
        this.y += adder;
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
        return (int) Math.sqrt(Math.pow(distX,2)+Math.pow(distY,2));
    }

    @Override
    public String toString() {
        return " " + x + " " + y;
    }
}