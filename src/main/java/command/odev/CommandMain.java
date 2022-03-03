package command.odev;

public class CommandMain {
    public static void main(String[] args) {
        Fan fan = new Fan();
        Command turnOnCommand = new TurnOnCommand(fan);
        Command turnOffCommand = new TurnOffCommand(fan);

        Remote remote = new Remote(turnOnCommand, turnOffCommand);
        remote.turnOnButtonClick();
        remote.turnOffButtonClick();
    }
}
