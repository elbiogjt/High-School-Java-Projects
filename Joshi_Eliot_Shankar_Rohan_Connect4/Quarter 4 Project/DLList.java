import java.io.Serializable;

public class DLList<E> implements Serializable {
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        size = 0;
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrev(head);
    }

    public Node<E> getNode(int index) {
        Node<E> cur = head.next();
        for (int i = 0; i < index; i++) {
            cur = cur.next();
        }
        return cur;
    }

    public boolean add(E element) {
        if (size == 0) {
            Node<E> n = new Node<>(element);
            head.setNext(n);
            n.setNext(tail);
            tail.setPrev(n);
            n.setPrev(head);
            size++;
            return true;
        } else {
            Node<E> n = new Node<>(element);
            Node<E> before = tail.prev();
            before.setNext(n);
            n.setNext(tail);
            n.setPrev(before);
            tail.setPrev(n);
            size++;
            return true;
        }
    }

    public void add(int index, E element) {
        //System.out.println("Adding: " + element + " At index: " + index);
        if (index == 0) {
            Node<E> after = head.next();
            Node<E> n = new Node<>(element);
            head.setNext(n);
            n.setNext(after);
            after.setPrev(n);
            n.setPrev(head);
            size++;
        } else if (index == size) {
            Node<E> before = tail.prev();
            Node<E> n = new Node<>(element);
            before.setNext(n);
            n.setNext(tail);
            tail.setPrev(n);
            n.setPrev(before);
            size++;
        } else {
            Node<E> cur = head.next();
            for (int i = 0; i < index-1; i++) {
                cur = cur.next();
            }
            Node<E> n = new Node<>(element);
            Node<E> tmp = cur.next();
            cur.setNext(n);
            n.setPrev(cur);
            n.setNext(tmp);
            tmp.setPrev(n);
            size++;
        }
    }

    public E get(int index) {
        return getNode(index).get();
    }


    public int size() {
        return size;
    }

    public E remove(int index) {
        Node<E> n = getNode(index);
        Node<E> before = n.prev();
        Node<E> after = n.next();
        if (before != null) {
            before.setNext(after);
        }
        
        if (after != null) {
            after.setPrev(before);
        }
        
        size--;
        return n.get();
    }

    public void remove(E element) {
        Node<E> cur = head.next();
        while (cur.next() != null) {
            if (cur.get().equals(element)) {
                Node<E> before = cur.prev();
                Node<E> after = cur.next();
                before.setNext(after);
                after.setPrev(before);
            }
            cur = cur.next();
        }

        size--;
    }

    public String toString() {
        String result = "";
        
        for (int i = 0; i < size(); i++) {
            result += get(i).toString() + ", ";
        }
        return result;
    }

    public String newToString() {
        String result = "";
        
        for (int i = 0; i < size(); i ++) {
            result += get(i);
        }
        return result;
    }

    public int count() {
        int result = 0;
        for (int i = 0; i < size(); i ++) {
            if (get(i) != null) {
                result ++;
            }
        }

        return result;
    }

    

}