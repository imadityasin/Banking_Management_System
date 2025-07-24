// Account.java
public class Account {
    private int accountNumber;
    private String name;
    private int hashedPin;
    private double balance;
    private int age;

    public Account(int accountNumber, String name, int hashedPin, double balance, int age) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.hashedPin = hashedPin;
        this.balance = balance;
        this.age=age;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public int getHashedPin() {
        return hashedPin;
    }

    public double getBalance() {
        return balance;
    }

    public double getAge() {
        return age;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}