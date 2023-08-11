import java.io.Serializable;

public class Country implements Serializable{
    private String abbrev;
    private String name;

    public Country(String abbrev, String name) {
        this.abbrev = abbrev;
        this.name = name;
    }

    public int hashCode() {
        int value1 = (((int)abbrev.toLowerCase().charAt(0)) - 97)*26;
        int value2 = (((int)abbrev.toLowerCase().charAt(1)) - 97);
        return value1 + value2;
    }

    public String toString() {
        return name + ": " + abbrev;
    }

    public boolean equals(Object a) {
        if (a.hashCode() == this.hashCode()) {
            return true;
        }
        return false;
    }
}
