public class MyHashMap<K, V> {
    public static final int DEFAULT_CAPACITY = 1000;

    private Object[] hashArray;
    private int size;
    private MyHashSet<K> keySet;

    public MyHashMap() {
        hashArray = new Object[DEFAULT_CAPACITY];
        size = 0;
        keySet = new MyHashSet<>();
    }

    @SuppressWarnings("unchecked")
    public V put(K key, V value) {
        int hash = key.hashCode();
        V prev = (V)hashArray[hash];
        hashArray[hash] = value;
        keySet.add(key);
        size++;
        return prev;
    }

    @SuppressWarnings("unchecked")
    public V get(Object e) {
        int hash = ((K)e).hashCode();
        return (V)hashArray[hash];
    }

    @SuppressWarnings("unchecked")
    public V remove(Object o) {
        int hash = ((K)o).hashCode();

        V res = (V)hashArray[hash];
        hashArray[hash] = null;
        keySet.remove((K)o); 

        if (res != null) {
            size--;
        }
        
        return res;
    }

    public int size() {
        return size;
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    public void clear() {
        hashArray = new Object[DEFAULT_CAPACITY];
        size = 0;
        keySet = new MyHashSet<>();
    }
}