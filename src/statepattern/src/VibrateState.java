
public class VibrateState extends State {
    private VibrateState() {}

    public static final VibrateState instance = new VibrateState();

    @Override
    public void ring() {
        System.out.println("Phone is vibrating");
    }

    @Override
    public void doThis() {
        System.out.println("Vibrate action is performed");
    }
}
