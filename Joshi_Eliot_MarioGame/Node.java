public class Node<E> {
    private E data;
    private Node<E> next;
    private Node<E> prev;

    public Node(E data){
        this.data=data;	
        next=null;
        prev = null;
    }
    
    public E get() {
        return data;
    }

    public Node<E> next(){
        return next;
    }

    public Node<E> prev(){
        return prev;
    }

    public void setData(E a) {
        data = a;
    }

    public void setNext(Node<E> a){
        next = a;
    }
    
    public void setPrev(Node<E> a){
        prev = a;
    }   
}