import java.awt.Graphics;

public class BinarySearchTree<E extends Comparable<E>>{
    private Node<E> root;
    private int passes;

    public BinarySearchTree(){
        root = null;
    }

    public void add(E e){
        passes = 0;
        root = add(e,root);
    }

    public int getPasses() {
        return passes;
    }

    private Node<E> add(E e, Node<E> node){
        passes++;
        if(node == null){
            return new Node<E>(e);
        }
        
        if (e.compareTo(node.get()) < 0){
            node.setLeft(add(e, node.getLeft()));
        } else if(e.compareTo(node.get()) > 0){
            node.setRight(add(e, node.getRight()));
        }
        return node;
    }

    public void drawMe(Graphics g, int x, int y) {
        drawMe(g, x, y, root);
    }

    public void drawMe(Graphics g, int x, int y, Node<E> node) {
        if (node != null) {
            int width = getHeight(node)*20;
            g.drawString(node.get().toString(),x,y);
            if (node.getLeft() != null) {
                drawMe(g, x-width, y+width, node.getLeft());
                g.drawLine(x,y,x-width,y+width);
            } 
            if (node.getRight() != null) {
                drawMe(g,x+width,y+width,node.getRight());
                g.drawLine(x,y,x+width,y+width);
            }
        } 
    }

    public String inOrderString(Node<E> node){
        String a = "";
        if (node == null){
            return a;
        }
        a = inOrderString(node.getLeft()) + node.get() + " " + inOrderString(node.getRight());
        return a;
    }

    public String toString(){
        return inOrderString(root);
    }

    public String toStringPreOrder() {
        String a = "";
        if (root == null) {
            return a;
        }
        a = toStringPreOrder(root);
        return a;
    }

    private String toStringPreOrder(Node<E> node) {
        String a = "";
        if (node == null) {
            return a;
        }
        a = node.get() + " " + toStringPreOrder(node.getLeft()) + toStringPreOrder(node.getRight());
        return a;
    }

    public boolean contains(E e) {
        return contains(e,root);
    }

    private boolean contains(E e, Node<E> node) {
        if (node == null) {
            return false;
        }
        if (e.compareTo(node.get()) == 0) {
            return true;
        } else if (e.compareTo(node.get()) < 0) {
            return contains(e,node.getLeft());
        } else if (e.compareTo(node.get()) > 0) {
            return contains(e,node.getRight());
        }
        return false;
    }

    public void clear() {
        root = null;
    }
    
    public void remove(E e) {
        if (contains(e)) {
            remove(e, root, null);
        }
    }

    private void remove(E e, Node<E> current, Node<E> parent) {
        if (current != null) {
            if (current.get().equals(e)) {
                if (current.getRight() == null && current.getLeft() == null) {
                    if (parent == null) {
                        root = null;
                        return;
                    }
                    if (parent.getRight() == current) {
                        parent.setRight(null);
                    }
                    if (parent.getLeft() == current) {
                        parent.setLeft(null);
                    }
                    return;
                } else if (current.getRight() == null) {
                    if (parent.getRight() == current) {
                        parent.setRight(current.getLeft());
                    }
                    if (parent.getLeft() == current) {
                        parent.setLeft(current.getLeft());
                    }
                    current = null;
                    return;
                } else if (current.getLeft() == null) {
                    if (parent.getRight() == current) {
                        parent.setRight(current.getRight());
                    }
                    if (parent.getLeft() == current) {
                        parent.setLeft(current.getRight());
                    }
                    current = null;
                    return;
                } else {
                    Node<E> temp = current.getRight();
                    while (temp.getLeft() != null) {
                        temp = temp.getLeft();
                    }
                    current.set(temp.get());
                    remove(temp.get(),current.getRight(),current);
                }
            }
            if (e.compareTo(current.get()) < 0) {
                remove(e,current.getLeft(),current);
            } 
            if (e.compareTo(current.get()) > 0) {
                remove(e,current.getRight(),current);
            }
        } 
    }

    public int getHeight() {
        return getHeight(root);
    }

    public int getHeight(Node<E> node) {
        if (node == null) {
            return -1;
        } else {
            int leftHeight = getHeight(node.getLeft());
            int rightHeight = getHeight(node.getRight());
            if (leftHeight > rightHeight) {
                return leftHeight+1;
            }
            return rightHeight+1;
        }
    }

    public int getLevel() {
        return getHeight() + 1;
    }

    public int getHeight(E e) {
        Node<E> node = getNode(e,root);
        if (node != null) {
            return getHeight(node);
        }
        return -1;
    }

    public Node<E> getNode(E e) {
        return getNode(e,root);
    }

    private Node<E> getNode(E e, Node<E> node) {
        passes++;
        if (node != null) {
            if (node.get().compareTo(e) == 0) {
                return node;
            } else if (e.compareTo(node.get()) < 0) {
                return getNode(e,node.getLeft());
            } else if (e.compareTo(node.get()) > 0) {
                return getNode(e,node.getRight());
            }
        }
        return null;
    }
}