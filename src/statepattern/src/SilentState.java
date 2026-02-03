
public class SilentState extends State {
    private SilentState() {}

    public static final SilentState instance = new SilentState();

    @Override
    public void ring() {
        System.out.println("Phone is muted");
    }

    @Override
    public void doThis() {
        System.out.println("Silent action is performed");
    }
}
