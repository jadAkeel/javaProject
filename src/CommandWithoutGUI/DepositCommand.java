package src.CommandWithoutGUI;

// cocnrete comamnd
public class DepositCommand implements Command {
    private BankAccount account;
    private int amount;

    public DepositCommand(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.deposit(amount);
    }

    @Override
    public void undo() {
        account.withdraw(amount);
    }
}
