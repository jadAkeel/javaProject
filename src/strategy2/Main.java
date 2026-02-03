package src.strategy2;

public class Main {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        cart.setPaymentStrategy(new CreditCardPayment("1234-5678"));
        cart.checkout(100.0);

        cart.setPaymentStrategy(new PayPalPayment("user@email.com"));
        cart.checkout(200.0);

        cart.setPaymentStrategy(new BitcoinPayment("1A2B3C"));
        cart.checkout(300.0);
    }
}
