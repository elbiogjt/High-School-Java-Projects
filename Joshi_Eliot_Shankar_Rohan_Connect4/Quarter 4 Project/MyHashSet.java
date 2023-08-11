public class MyHashSet<E> {
    private static final int MAX_MEMORY_ALLOCATED = 100000;
    
    private Object[] memory;
    private int size;

    public MyHashSet() {
	memory = new Object[MAX_MEMORY_ALLOCATED];
	size = 0;
    }

    public boolean add(E e) {
	if(memory[Math.abs(e.hashCode() % MAX_MEMORY_ALLOCATED)] == null) {
	    memory[Math.abs(e.hashCode() % MAX_MEMORY_ALLOCATED)] = e;
	    //does allow for collisions but prevents overflow issues
	    size++;
	    return true;
	}
	return false;
    }

    @SuppressWarnings("unchecked")
    public E get(E data) {
        return (E)memory[Math.abs(data.hashCode() % MAX_MEMORY_ALLOCATED)];
    }

    public void clear() {
        memory = new Object[MAX_MEMORY_ALLOCATED];
        size = 0;
	//memory leak but cleaned up
    }

    public boolean contains(E e) {
	    return memory[Math.abs(e.hashCode() % MAX_MEMORY_ALLOCATED)] != null;
    }

    public boolean remove(E e) {
	if(memory[Math.abs(e.hashCode() % MAX_MEMORY_ALLOCATED)] != null) {
	    memory[Math.abs(e.hashCode() % MAX_MEMORY_ALLOCATED)] = null;
	    size--;
	    return true;
	}
	return false;
    }

	@SuppressWarnings("unchecked")
	public String toString() {
        String str = "";
        for(Object e : memory) {
            if(e != null) {
                str += ((E)e).toString() + "\n";
            }
        }
        return str;
    }

    public int size() {
		return size;
    }

    @SuppressWarnings("unchecked")
	public DLList<E> toDLList() {
	DLList<E> l = new DLList<>();
	
	for(Object e : memory) {
	    if(e != null) {
			l.add((E)e);
	    }
	}
	return l;
    }

    /*
    public Iterator<E> iterator() {
	return new HashSetIterator(this) {
	    
	};
    }
    */
}