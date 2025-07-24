// AccountCreation.java
import java.sql.*;
import java.util.Scanner;

public class AccountCreation {

    public void createAccount(Account acc) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "INSERT INTO accounts (account_number, name, pin, balance) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, acc.getAccountNumber());
            pst.setString(2, acc.getName());
            pst.setInt(3, acc.getHashedPin());
            pst.setDouble(4, acc.getBalance());

            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Account Created Successfully");
            } else {
                System.out.println("Failed to create Account");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void login(int accNo, int pin) {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT * FROM accounts WHERE account_number = ? AND pin = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, accNo);
            pst.setInt(2, pin);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                String name = rs.getString("name");
                System.out.println("Login Successful! Welcome " + name);

                Scanner sc = new Scanner(System.in);
                while (true) {
                    System.out.println("\n ------ Account Menu ------");
                    System.out.println("1. Check Balance");
                    System.out.println("2. Deposit Money");
                    System.out.println("3. Withdraw Money");
                    System.out.println("4. Logout");
                    System.out.print("Enter Your Choice: ");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            checkBalance(accNo);
                            break;
                        case 2:
                            System.out.print("Enter Amount to Deposit: ");
                            double depositAmt = sc.nextDouble();
                            deposit(accNo, depositAmt);
                            break;
                        case 3:
                            System.out.print("Enter Amount to Withdraw: ");
                            double withdrawAmt = sc.nextDouble();
                            withdraw(accNo, withdrawAmt);
                            break;
                        case 4:
                            System.out.println("Logged out Successfully");
                            return;
                        default:
                            System.out.println("Invalid Choice");
                    }
                }
            } else {
                System.out.println("Incorrect Account Number or PIN");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkBalance(int accNo) throws SQLException {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setInt(1, accNo);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                double balance = rs.getDouble("balance");
                System.out.println("Your Current Balance: ₹" + balance);
            }
        }
    }

    private void deposit(int accNo, double amount) throws SQLException {
        try (Connection conn = DatabaseConnection.connect()) {
            String sql = "UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setDouble(1, amount);
            pst.setInt(2, accNo);
            int rows = pst.executeUpdate();
            if (rows > 0) {
                System.out.println("Amount Deposited Successfully!");
            }
        }
    }

    private void withdraw(int accNo, double amount) throws SQLException {
        try (Connection conn = DatabaseConnection.connect()) {
            String checkSql = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement checkPst = conn.prepareStatement(checkSql);
            checkPst.setInt(1, accNo);
            ResultSet rs = checkPst.executeQuery();
            if (rs.next()) {
                double currentBal = rs.getDouble("balance");
                if (currentBal >= amount) {
                    String updateSql = "UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
                    PreparedStatement updatePst = conn.prepareStatement(updateSql);
                    updatePst.setDouble(1, amount);
                    updatePst.setInt(2, accNo);
                    updatePst.executeUpdate();
                    System.out.println("Amount Withdrawn Successfully!");
                } else {
                    System.out.println("Insufficient Balance!");
                }
            }
        }
    }
}