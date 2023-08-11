public class Graph<E> {
    private MyHashMap<E, MyHashSet<E>> graph;
    private int size = 0;
    
    public Graph() {
        graph = new MyHashMap<>();
    }

    public void add(E e) {
        if (!graph.keySet().contains(e)) {
            graph.put(e, new MyHashSet<E>());
        }
        size++;
    }

    public void addEdge(E a, E b) {
        MyHashSet<E> graphset = graph.keySet();
        a = graphset.get(a);
        b = graphset.get(b);
        graph.get(a).add(b);
        graph.get(b).add(a);
    }

    public void removeEdge(E a, E b) {
        graph.get(a).remove(b);
        graph.get(b).remove(a);
    }

    public void remove(E e){
        graph.remove(e);
        DLList<E> graphset = graph.keySet().toDLList();
        for (int i=0; i<graphset.size(); i++) {
            graph.get(graphset.get(i)).remove(e);
        }
        size--;
    }

    public MyHashMap<E, MyHashSet<E>> getHashMap() {
        return graph;
    }

    public String toString() {
        String text = "";
        DLList<E> graphset = graph.keySet().toDLList();
        for (int i=0; i<graphset.size(); i++) {
            text += graphset.get(i) + " -> ";
            DLList<E> set = graph.get(graphset.get(i)).toDLList();
            for (int j=0; j<set.size(); j++) {
                text += set.get(j).toString() + " ";
            }
            text += "\n\n";
        }
        return text;
    }

    public MyHashSet<E> getKeySet() {
        return graph.keySet();
    }

    public E get(E e) {
        MyHashSet<E> graphset = graph.keySet();
        if (graphset.contains(e)) {
            e = graphset.get(e);
            return e;
        }
        return null;
    }

    public E depthFirstSearch(E current, E e, MyHashSet<E> visited){
        if (visited.contains(current) == false){
            visited.add(current);
            if (current.equals(e)){
                return current;
            } 
            MyHashSet<E> edges = graph.get(current);
            MyHashSet<E> nodesToVisit = new MyHashSet<E>();
            for (E el : edges){
                if (visited.contains(el) == false) {
                    nodesToVisit.add(el);
                }
            }
            for (E each: nodesToVisit){
                E res = depthFirstSearch(each, e, visited);
                if (res != null) {
                    return res;
                }
            }
            
        }
        return null;
    }

    public E breadthFirstSearch(E node, E e){
        MyHashSet<E> visited = new MyHashSet<E>();
        MyHashSet<E> nodesToVisit = new MyHashSet<E>();
        nodesToVisit.add(node);
        while (visited.size() < graph.size()){
            for (E each: nodesToVisit){
                visited.add(each);
                if (e.equals(each)) {
                    return each;
                }
            }
            MyHashSet<E> newList = new MyHashSet<E>();
            for (E each: nodesToVisit){
                for (E val: graph.get(each)){
                    if (visited.contains(val) == false){
                        newList.add(val);
                    }
                }
            }
            nodesToVisit = newList;
        }
        return null;
    }
}