package src.CommandWithoutGUI;

public class Main {
    public static void main(String[] args) {

        BankAccount account = new BankAccount();
        TransactionManager manager = new TransactionManager();

        Command deposit100 = new DepositCommand(account, 100);
        Command withdraw50 = new WithdrawCommand(account, 50); }
}