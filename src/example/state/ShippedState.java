package src.example.state;

import src.example.template.Order;

public class ShippedState implements OrderState {
    public void next(Order order) {
        order.setState(new DeliveredState());
    }
    public void printStatus() {
        System.out.println("Order shipped");
    }
}