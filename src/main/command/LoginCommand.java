package main.command;

import command.AccessControlManager;

public class LoginCommand implements Command {
    private AccessControlManager accessControlManager;
    private String employeeId;
    private String password;

    public LoginCommand(AccessControlManager accessControlManager, String employeeId, String password) {
        this.accessControlManager = accessControlManager;
        this.employeeId = employeeId;
        this.password = password;
    }

    @Override
    public void execute() {
        accessControlManager.login(employeeId, password);
    }
}
