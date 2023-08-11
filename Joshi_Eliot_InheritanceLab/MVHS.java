public abstract class MVHS {
    private String name;

    public MVHS(String a) {
        name = a;
    }

    public abstract String getRole();
    public String getName() {
        return name;
    }

    public String toString() {
        return getName() + ": " + getRole();
    }
}