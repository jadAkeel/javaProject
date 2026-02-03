package src.example.state;

import src.example.template.Order;

public class DeliveredState implements OrderState {
    public void next(Order order) {
        System.out.println("Order already delivered");
    }
    public void printStatus() {
        System.out.println("Order delivered");
    }
}