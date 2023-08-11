import java.util.ArrayList;

public class Schedule {
    private ArrayList<Pair<Integer,String>> mySchedule;

    public Schedule() {
        mySchedule = new ArrayList<Pair<Integer,String>>();
    }

    public void addClass(int a, String b) {
        boolean f = false;
        for (int i=0; i<mySchedule.size(); i++) {
            if (mySchedule.get(i).getObjectT() == a) {
                f = true;
            }
        }
        if (f==false) {
            mySchedule.add(new Pair<Integer,String>(a,b));
        }
    }

    public void sortClasses() {
        for (int i = 0; i < mySchedule.size() - 1; i++) {
            for (int j = 0; j < mySchedule.size() - i - 1; j++) {
                if (mySchedule.get(j + 1).getObjectT() < mySchedule.get(j).getObjectT()) {
                    Pair<Integer,String> temp = mySchedule.get(j + 1);
                    mySchedule.set(j + 1, mySchedule.get(j));
                    mySchedule.set(j, temp);
                }
            }
        }
    }

    public String toString() {
        String a = "";
        sortClasses();
        for (int i=0;i<mySchedule.size();i++) {
            a += mySchedule.get(i).toString();
            a += "\n";
        }
        return a;
    }
}