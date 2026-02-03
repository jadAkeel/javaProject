package src.example.template;

import src.example.state.NewOrderState;
import src.example.state.OrderState;
import src.example.strategy.PaymentStrategy;

public abstract class Order {

    protected PaymentStrategy paymentStrategy;
    protected OrderState state;

    public Order() {
        state = new NewOrderState();
    }

    public final void processOrder() {
        validate();
        prepare();
        pay();
        ship();
    }

    protected void validate() {
        System.out.println("Validating order");
    }

    protected abstract void prepare();

    protected void pay() {
        paymentStrategy.pay(100);
    }

    protected void ship() {
        state.next(this);
        state.printStatus();
    }

    public void setPaymentStrategy(PaymentStrategy p) {
        this.paymentStrategy = p;
    }

    public void setState(OrderState state) {
        this.state = state;
    }
}