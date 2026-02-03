package src.example.factory;

import src.example.template.Order;

public abstract class OrderFactory {
    public abstract Order createOrder();
}