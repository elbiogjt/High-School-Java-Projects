public class Node<E> {
    private E obj;
    private Node<E> next;
    private Node<E> prev;

    public Node(E a) {
        obj = a;
        next = null;
        prev = null;
    }

    public E get() {
        return obj;
    }

    public void set(E obj) {
        this.obj = obj;
    }

    public Node<E> prev() {
        return prev;
    }

    public Node<E> next() {
        return next;
    }

    public void setNext(Node<E> obj2) {
        next = obj2;
    }

    public void setPrev(Node<E> obj) {
        prev = obj;
    }

    public void setData(E newData) {
        obj = newData;
    }



}
