
public abstract class State {
    protected Mobile mobile;

    public void setMobile(Mobile mobile) {
        this.mobile = mobile;
    }

    public abstract void ring();
    public abstract void doThis();
}
