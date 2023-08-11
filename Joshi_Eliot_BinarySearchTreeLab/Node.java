public class Node<E> {
    private E data;
    private Node<E> left;
    private Node<E> right;

    public Node(E e) {
        data = e;
        left = null;
        right = null;
    }

    public E get() {
        return data;
    }

    public void set(E e) {
        data = e;
    }

    public Node<E> getLeft() {
        return left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }
}