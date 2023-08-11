public class MyHashMap<K, V> {
    private Object[] hashArray;
    private int size;
    private final int SZ = 100000;
    private MyHashSet<K> keySet;

    public MyHashMap() {
        size = 0;
        hashArray = new Object[SZ];
        keySet = new MyHashSet<>();
    }

    public void clear() {
        size = 0;
        hashArray = new Object[SZ];
        keySet = new MyHashSet<>();
    }

	@SuppressWarnings("unchecked")
    public V put(K key, V value) {
        int index = Math.abs(key.hashCode()%SZ);
        V v = (V)(hashArray[index]);
        hashArray[index] = value;
        size++;
        keySet.add(key);
        return v;
    }

	@SuppressWarnings("unchecked")
    public V get(Object key) {
        K k = (K) key;
        return (V)(hashArray[Math.abs(k.hashCode()%SZ)]);
    }

	@SuppressWarnings("unchecked")
    public V remove(Object key) {
        K k = (K) key;
        V value = (V)(hashArray[k.hashCode()%SZ]);
        hashArray[Math.abs(k.hashCode()%SZ)] = null;
        size--;
        keySet.remove((K)key);
        return value;
    }

    public int size() {
        return size;
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    public String toString() {
        String s = "[";
        DLList<K> list = keySet.toDLList();
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i) + ", ";
        }
        s = s.substring(0, s.length()-2);
        s += "]";
        return s;
    }
}