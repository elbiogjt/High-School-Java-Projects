public class State {
    private String name;
    private String abrev;

    public State(String name, String abrev) {
        this.name = name;
        this.abrev = abrev.toLowerCase();
    }

    public int hashCode() {
        int value1 = (((int)abrev.charAt(0)) - 97)*26;
        int value2 = (((int)abrev.charAt(1)) - 97);
        return value1 + value2;
    }

    public String toString() {
        return name + "- " + abrev.toUpperCase();
    }
}
