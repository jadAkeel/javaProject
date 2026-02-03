package src.strategy2;

public class BitcoinPayment implements PaymentStrategy {
    private String wallet;

    public BitcoinPayment(String wallet) {
        this.wallet = wallet;
    }

    @Override
    public void pay(double amount) {
        System.out.println(amount + "$ Bitcoin " + wallet);
    }
}
