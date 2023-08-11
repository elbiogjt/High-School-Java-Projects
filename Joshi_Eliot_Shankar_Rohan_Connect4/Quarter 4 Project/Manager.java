public class Manager {

    private MyHashMap<Thread, ServerThread> threadList;

    public Manager() {
        threadList = new MyHashMap<>();
    }

    public void add(Thread t, ServerThread s) {
        threadList.put(t, s);
        t.start();
        //t.run();
    }

    public void broadcast(Object s, Thread sender) { //int coordinates (x,y), String color (red/yello)
        for (int i = 0; i < threadList.size(); i++) {
            DLList<Thread> t = threadList.keySet().toDLList();
            ServerThread st = threadList.get(t.get(i));
            if (t.get(i) != sender) {
                //t.get(i).start();
                st.send(s);
                System.out.println("sent object");
            }
        }
    }
}