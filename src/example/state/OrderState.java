package src.example.state;

import src.example.template.Order;

public interface OrderState {
    void next(Order order);
    void printStatus();
}