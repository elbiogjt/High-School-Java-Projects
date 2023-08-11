public class Graph<E> {
    private MyHashMap<E, MyHashMap<E, Integer>> graph;
    private int size = 0;
    
    public Graph() {
        graph = new MyHashMap<E, MyHashMap<E, Integer>>();
    }

    public void add(E e) {
        if (!graph.keySet().contains(e)) {
            graph.put(e, new MyHashMap<E, Integer>());
        }
        size++;
    }

    public void addEdge(E a, E b, int weight) {
        MyHashSet<E> graphset = graph.keySet();
        a = graphset.get(a);
        b = graphset.get(b);
        graph.get(a).put(b,weight);
        graph.get(b).put(a,weight);
    }

    public MyHashMap<E,Integer> getEdges(E a) {
        return graph.get(a);
    }

    public int getWeight(E a, E b) {
        return graph.get(a).get(b);
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

    public MyHashMap<E, MyHashMap<E,Integer>> getHashMap() {
        return graph;
    }

    public String toString() {
        String text = "";
        DLList<E> graphset = graph.keySet().toDLList();
        for (int i=0; i<graphset.size(); i++) {
            text += graphset.get(i) + " -> ";
            DLList<E> set = graph.get(graphset.get(i)).keySet().toDLList();
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

    public DLList<E> distance(E start, E end) {
        MyHashMap<E, Integer> distances = new MyHashMap<E,Integer>();
        MyHashMap<E, E> prevList = new MyHashMap<E,E>();
        MyHashSet<E> visited = new MyHashSet<E>();
        DLList<E> graphset = graph.keySet().toDLList();
        for (int i=0; i<graphset.size(); i++) {
            distances.put(graphset.get(i), Integer.MAX_VALUE);
        }
        distances.put(start, 0);
    
        while (!visited.contains(end)) {
            E currentNode = null;
            int shortestDistance = Integer.MAX_VALUE;
            for (int i=0; i<graphset.size(); i++) {
                int nodeDistance = distances.get(graphset.get(i));
                if (!visited.contains(graphset.get(i)) && nodeDistance < shortestDistance) {
                    currentNode = graphset.get(i);
                    shortestDistance = nodeDistance;
                }
            }
    
            visited.add(currentNode);
            MyHashMap<E, Integer> neighbors = graph.get(currentNode);
            for (E neighbor : neighbors.keySet()) {
                int distance = neighbors.get(neighbor);
                if (!visited.contains(neighbor)) {
                    int newDistance = distances.get(currentNode) + distance;
                    if (newDistance < distances.get(neighbor)) {
                        distances.put(neighbor, newDistance);
                        prevList.put(neighbor, currentNode);
                    }
                }
            }
        }
    
        DLList<E> shortest = new DLList<E>();
        E previousNode = end;
        while (prevList.contains(previousNode)) {
            shortest.add(previousNode);
            previousNode = prevList.get(previousNode);
        }
        shortest.add(start);
        return shortest;
    }
}