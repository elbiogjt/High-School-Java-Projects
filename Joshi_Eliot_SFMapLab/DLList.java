public class DLList<E> {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        size = 0;
        head = new Node<E>(null);
        tail = new Node<E>(null);

        tail.setPrev(head);
        head.setNext(tail);
		head.setPrev(null);
		tail.setNext(null);

    }

    public Node<E> getNode(int i) {
        if(size == 0){
            return null;
        }else{
            Node<E> current = head.next();
            int counter = 0;
            while(current != null){
                if(counter == i){
                    return current;
                }
                counter++;
                current = current.next();
            }
            return current;
        }
    }

    public boolean add(E x) {
        Node<E> ad = new Node<E>(x);
        Node<E> before = tail.prev();
        Node<E> after = tail;
        ad.setPrev(before);
        ad.setNext(after);
        before.setNext(ad);
        after.setPrev(ad);
        size++;
        return true;
    }

    public boolean add(E x, int i) {
        Node<E> ad = new Node<E>(x);
        Node<E> current = head.next();
        if (i == 0) {
            ad.setNext(head.next());
            head.setNext(ad);
            size++;
            return true;
        } else {
            for (int j = 0; j < i - 1; j++) {
                current = current.next();
            }
            ad.setNext(current.next());
            current.setNext(ad);
            size++;
            for (int j = i; j < size; j++) {
                current = current.next();
            }
        }
        return true;
    }

    public void deleteFirstNode() {
        if (head == null) {
            System.out.println("List is empty");
        }
        if (head.next() == null) {
            tail = null;
        } else {
            head.next().setPrev(null);
        }
        head = head.next();
        size--;
    }

    public void deleteLastNode() {
        if (tail == null) {
            throw new RuntimeException("List is empty");
        }
        if (head.next() == null) {
            head = null;
        } else {
            tail.prev().setNext(null);
        }
        tail = tail.prev();
        size--;
    }

    public void remove(int i) {
        Node<E> current = head.next();
        if (i == 0) {
            head.setNext(current.next());
            current.next().setPrev(head);
            size--;
        } else {
            for (int j = 0; j < i; j++) {
                current = current.next();
            }
            Node<E> before = current.prev();
            Node<E> after = current.next();
            before.setNext(after);
            after.setPrev(before);
            size--;
        }
    }

    public void remove(E element) {
        Node<E> current = head.next();
        while (current.next() != null) {
            if (current.get().equals(element)) {
                Node<E> before = current.prev();
                Node<E> after = current.next();
                before.setNext(after);
                after.setPrev(before);
            }
            current = current.next();
        }
        size--;
    }

    public E get(int a) {
        Node<E> x = getNode(a);
        return x.get();
    }

    public E get(E e) {
        Node<E> current = head.next();
        while (current != null) {
            if (current.equals(e)) {
                break;
            }
            current = current.next();
        }
        return current.get();
    }

    public int size() {
        return size;
    }

    public void set(int i, E x) {
        Node<E> current = getNode(i);

        current.setData(x);
    }

    public String toString() {
        String concat = "";
        Node<E> current = head.next();
        for (int i = 0; i < size; i++) {
            concat = concat + current.get() + "\n";
            current = current.next();
        }

        return concat;
    }
}