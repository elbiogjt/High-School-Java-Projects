public class Pair<E>{
    private E prev;
    private int dist;

    public Pair(int dist, E prev){
        this.dist = dist;
        this.prev = prev;
    }

    public int get(){
        return dist;
    }

    public void set(int dist, E e){
        this.dist = dist;
        this.prev = e;
    }

    public E getPrev(){
        return prev;
    }

    public String toString() {
        return prev + ": " + dist + " miles";
    }
}