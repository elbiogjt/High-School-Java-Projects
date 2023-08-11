public class Location {
    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int hashCode() {
        return x*117 + y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
