public class Student {
    private String firstname;
    private String lastname;
    private int age;

    public Student(String a, String b, int c) {
        firstname= a;
        lastname= b;
        age= c;
    }

    public String toString() {
        return lastname + ", " + firstname + ": " + age + "y/o";
    }

    public String getLastName() {
        return lastname;
    }
}