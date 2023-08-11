public class Pair<T,S> {
    T t;
    S s;

    Pair(T t, S s) {
        this.t=t;
        this.s=s;
    }

    public T getObjectT() {
        return t;
    }

    public S getObjectS() {
        return s;
    }

    public String toString() {
        return t + ": " + s;
    }
}