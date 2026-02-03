package src.example.factory;

import src.example.template.Order;

public class OnlineOrder extends Order {
    protected void prepare() {
        System.out.println("Preparing online order");
    }
}