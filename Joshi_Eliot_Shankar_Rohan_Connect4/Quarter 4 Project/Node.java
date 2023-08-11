public class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> prev;

    public Node(E element) {
        data = element;
    }

    public E get() {
        return data;
    }

    public void set(E element) {
        data = element;
    }

    public Node<E> next() {
        return next;
    }

    public Node<E> prev() {
        return prev;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    public void setPrev(Node<E> prev) {
        this.prev = prev;
    }
}