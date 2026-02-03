package src.CommandWithoutGUI;

public class WithdrawCommand implements Command {
    private BankAccount account;
    private int amount;

    public WithdrawCommand(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.withdraw(amount);
    }

    @Override
    public void undo() {
        account.deposit(amount);
    }
}
