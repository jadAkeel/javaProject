package src.example.command;

import src.example.template.Order;

public class PlaceOrderCommand implements Command {

    private Order order;

    public PlaceOrderCommand(Order order) {
        this.order = order;
    }

    public void execute() {
        order.processOrder();
    }
}
