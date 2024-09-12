package main.command;

public class EmployeeActionInvoker {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void executeAction() {
        command.execute();
    }
}
