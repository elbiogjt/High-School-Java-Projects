public class Account implements Comparable<Account> {
    private String lastName;
    private String firstName;
    private int pin;
    private double balance;

    public Account(String lastName, String firstName, int pin, double balance) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.pin = pin;
        this.balance = balance;
    }

    public String toStringFull() {
        return lastName + ", " + firstName + ": $" + balance + "                   \tPin: " + pin + "\n";
    }

    public String toString() {
        return lastName + ", " + firstName + "\n";
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void setBal(double a) {
        balance = balance + a;
    }

    @Override
    public int compareTo(Account o) {
        if (getLastName().equals(o.getLastName()) && getFirstName().equals(o.getFirstName())) {
            return 0;
        } else if (lastName.equals(o.getLastName())) {
            return firstName.compareTo(o.getFirstName());
        } else {
            return lastName.compareTo(o.getLastName());
        }
    }

}
