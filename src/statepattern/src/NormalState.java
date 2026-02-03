
public class NormalState extends State {
    private NormalState() {}

    public static final NormalState instance = new NormalState();

    @Override
    public void ring() {
        System.out.println("Phone is ringing");
    }

    @Override
    public void doThis() {
        System.out.println("Normal action is performed");
    }
}
