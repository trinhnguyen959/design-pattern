package command.odev;

public class Remote {
    private Command turnOnCommand;
    private Command turnOffCommand;

    public Remote(Command turnOnCommand, Command turnOffCommand) {
        this.turnOnCommand = turnOnCommand;
        this.turnOffCommand = turnOffCommand;
    }

    public void turnOnButtonClick() {
        turnOnCommand.execute();
    }

    public void turnOffButtonClick() {
        turnOffCommand.execute();
    }
}
