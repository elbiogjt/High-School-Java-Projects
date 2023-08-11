public class MyHashTable<K,V> {
    private DLList<V>[] table;
    private MyHashSet<K> keySet;

    @SuppressWarnings("unchecked") 
    public MyHashTable() {
        table = new DLList[100000];
        keySet = new MyHashSet<K>();
    }

    public boolean put(K k, V v) {
        if (table[k.hashCode()] == null) {
            table[k.hashCode()] = new DLList<>();
        }
        table[k.hashCode()].add(v);
        return keySet.add(k);
    }

    public DLList<V> get(K k) {
        if (table[k.hashCode()] != null) {
            return table[k.hashCode()];
        }
        return null;
    }

    public MyHashSet<K> keySet() {
        return keySet;
    }

    public String toString() {
        String line = "";
        for (K k: keySet) {
            line += k.hashCode() + ": " + table[k.hashCode()] + "\n";
        }
        return line;
    }

    public void remove(K k, int v) {
        if (!keySet.contains(k)) { 
            return; 
        }
        table[k.hashCode()].remove((int)v);
        if (table[k.hashCode()].size() == 0) { 
            table[k.hashCode()] = null;
            keySet.remove(k);
        }
    }

    public void remove(K k) {
        if (!keySet.contains(k)) { 
            return; 
        }
        table[k.hashCode()] = null;
        keySet.remove(k);
    }
}