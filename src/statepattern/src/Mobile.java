
public class Mobile {
    private State state;

    public void setState(State state) {
        this.state = state;
    }

    public void ring() {
        state.ring();
    }

    public void doThis() {
        state.doThis();
    }
}
