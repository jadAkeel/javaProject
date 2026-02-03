package src.example.state;

import src.example.template.Order;

public class NewOrderState implements OrderState {
    public void next(Order order) {
        order.setState(new ShippedState());
    }
    public void printStatus() {
        System.out.println("Order created");
    }
}