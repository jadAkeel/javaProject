package src.CommandWithGUI2;




public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        Fan fan = new Fan();

        Command lightOn = new LightOnCommand(light);
        Command fanStart = new FanStartCommand(fan);

        RemoteControl remote = new RemoteControl();

        remote.pressButton(lightOn);
        remote.pressButton(fanStart);

        remote.undo();
        remote.redo();
    }
}
