package src.example.factory;

import src.example.template.Order;

public class OnlineOrderFactory extends OrderFactory {
    public Order createOrder() {
        return new OnlineOrder();
    }
}