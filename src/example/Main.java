package src.example;

import src.example.command.Command;
import src.example.command.PlaceOrderCommand;
import src.example.factory.OnlineOrderFactory;
import src.example.factory.OrderFactory;
import src.example.strategy.CreditCardPayment;
import src.example.template.Order;

public class Main {
    public static void main(String[] args) {
        OrderFactory factory = new OnlineOrderFactory();
        Order order = factory.createOrder();

        order.setPaymentStrategy(new CreditCardPayment());

        Command placeOrder = new PlaceOrderCommand(order);
        placeOrder.execute();
    }
}