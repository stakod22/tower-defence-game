package game.path;

public class PathSegment {
    public int lenght;
    public int direction;

    public PathSegment(int lenght, int direction) {
        this.lenght = lenght;
        this.direction = direction;
    }

    public int getLenght() {
        return lenght;
    }

    public int getDirection() {
        return direction;
    }
}
