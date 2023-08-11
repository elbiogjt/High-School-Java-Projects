import java.io.Serializable;
public class Pair<K, V>  implements Serializable{
    public K key;
    public V value;
    

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public String toString() {
        return "(" + key + ", " + value + ")";
    }

    public String keyString() {
        return key.toString();
    }

    public int hashCode() {
        return (10 * key.hashCode()) + value.hashCode();
    }
}