import java.util.Iterator;

public class MyHashSet<E> implements Iterable<E> {
    public static final int ALLOCATED_LENGTH = 100000;
    private Object[] hashTable;
    private int size;
    
    public MyHashSet() {
        hashTable = new Object[ALLOCATED_LENGTH];
        size = 0;
    }   

    @SuppressWarnings("unchecked")
    public E get(E e) {
        E ans = (E) hashTable[indexOf(e)];
        return ans;
    }

    public boolean add(E add) {
        if (!contains(add)) {
            hashTable[indexOf(add)] = add;
            size++;
            return true;
        }
        return false;
    }

    public void clear() {
        hashTable = new Object[ALLOCATED_LENGTH];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public boolean contains(E check) {
        E against = (E) hashTable[indexOf(check)];
        if (against == null) {
            return false;
        }
        return against.equals(check);
    }

    public boolean remove(E remove) {
        hashTable[indexOf(remove)] = null;
        return true;
    }

    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    public DLList<E> toDLList() {
        DLList<E> res = new DLList<>();
        for (Object o : hashTable) {
            if (o != null) {
                res.add((E)o);
            }
        }
        return res;
    }

    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int current = 0;
            private int lastIndex = 0;

            public boolean hasNext() {
                return current != size;
            }

            @SuppressWarnings("unchecked")
            public E next() {
                current++;
                E res = null;
                for (int i = lastIndex; i < hashTable.length; i++) {
                    if (hashTable[i] != null) {
                        res = (E)hashTable[i];
                        i++;
                        lastIndex = i;
                        break;
                    }
                }
                return res;
            }
        };
    }

    private int indexOf(E item) {
        return item.hashCode();
    }
}