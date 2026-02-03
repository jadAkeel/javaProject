package src.CommandWithGUI2;

public class FanStopCommand implements Command {
    private Fan fan;
    public FanStopCommand(Fan fan) { this.fan = fan; }
    public void execute() { fan.stop(); }
    public void undo() { fan.start(); }
}
