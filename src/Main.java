// Main.java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountCreation ac = new AccountCreation();

        while (true) {
            System.out.println("======= 🏦 STATE BANK OF INDIA 🏦 =======");
            System.out.println("======= Banking Menu =======");

            System.out.println("1. Create Account");
            System.out.println("2. Login Account");
            System.out.println("3. Exit");

            System.out.print("Please Select: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1:
                    System.out.print("Enter Account Number: ");
                    long accNoLong = sc.nextLong();
                    int accNo = (int)(accNoLong % Integer.MAX_VALUE); // or handle with long in DB

                    sc.nextLine(); // consume newline
                    System.out.print("Enter Your Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt(); // currently not stored but can be added in DB
                    System.out.print("Set PIN: ");
                    int pin = sc.nextInt();

                    Account acc = new Account(accNo, name, pin, 0.0, age);
                    ac.createAccount(acc);
                    break;

                case 2:
                    System.out.print("Enter Account Number: ");
                    long accLoginLong = sc.nextLong();
                    int accLogin = (int)(accLoginLong % Integer.MAX_VALUE);
                    System.out.print("Enter PIN: ");
                    int pinLogin = sc.nextInt();
                    ac.login(accLogin, pinLogin);
                    break;

                case 3:
                    System.out.println("Thank You for choosing our System.");
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice");
                    System.out.println("Try Again");
            }
        }
    }
}
