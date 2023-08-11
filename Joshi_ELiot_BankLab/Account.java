public class Account{
    private String name;
    private double balance;
    private int pin;
    private boolean access;
   
    public Account(String name, double balance, int pin){
        this.name = name;
        this.balance = balance;
        this.pin = pin;
        this.access = false;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    public void changePin(int newPin) {
        this.pin = newPin;
    }
   
    public String getName(){
        if( access == true){
            return name;
        } else {
            return "no access";
        }
    }
   
    public double getBal() {
        return balance;
    }
   
    public void disableAccess() {
        access = false;
    }
   
    public boolean getAccess() {
        return access;
    }
   
    public void withdraw(double x) {
        if (balance - x < 0) {
            balance = balance;
        } else { 
            balance = balance - x;
        }
    }
   
    public void deposit(double y) {
        balance = balance + y;
    }
   
    public String updateName(String nam) {
        return nam;
    }
   
    public boolean checkPin(int pinInput, String username){
        if((pin == pinInput) && (username.equals(this.name))){
            access = true;
        } else {
            access = false;
        }
        return access;
    }
}