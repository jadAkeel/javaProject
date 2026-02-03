package src.CommandWithoutGUI;

//reicever
public class BankAccount {
    private int balance = 0;

    public void deposit(int amount) {
        balance += amount;
        System.out.println("Deposit: " + amount + " | Balance = " + balance);
    }

    public void withdraw(int amount) {
        balance -= amount;
        System.out.println("Withdraw: " + amount + " | Balance = " + balance);
    }

    public int getBalance() {
        return balance;
    }
}
