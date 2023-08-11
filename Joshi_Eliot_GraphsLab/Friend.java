public class Friend {
    private String name;
    private String school;
    private String email;
    private int id;
    private String passcode;
    private int totype;

    public Friend(String name, String school, String email, int id, String passcode) {
        this.name = name;
        this.school = school;
        this.email = email;
        this.id = id;
        this.passcode = passcode;
        totype = 0;
    }

    public int hashCode() {
        return id;
    }

    public String getPass() {
        return passcode;
    }

    public String getName() {
        return name;
    }

    public void setType(int type) {
        totype = type;
    }

    public boolean checkPass(String pass) {
        if (passcode.equals(pass)) {
            return true;
        }
        return false;
    }

    public String toString() {
        if (totype == 0) {
            return id + ": " + name;
        } else if (totype == 1) {
            return id + ": " + name + "\tSchool: " + school + "\temail: " + email;
        } else {
            return id + ", " + name + "; Pass: " + passcode;
        }
    }
}
