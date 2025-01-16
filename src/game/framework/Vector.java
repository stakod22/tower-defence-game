package game.framework;

public class Vector {
    public float x;
    public float y;

    public Vector(float x, float y) {
        this.x = x;
        this.y = y;
    }

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

    public void add(float adder){
        this.x += adder;
        this.y += adder;
    }

    public void divide(float other){
        this.x /= other;
        this.y /= other;
    }

    public double distanceToOther(Vector other){
        if (other == null){
            return -1;
        }
        float distX = this.x - other.x;
        float distY = this.y - other.y;
        if (distY < 0){
            distY *= -1;
        }
        if (distX <0){
            distX *= -1;
        }
        return Math.sqrt(Math.pow(distX,2)+Math.pow(distY,2));
    }

    @Override
    public String toString() {
        return " " + x + " " + y;
    }
}