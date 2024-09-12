package main.command;

import command.AccessControlManager;


public class LogoutCommand implements Command {
    private AccessControlManager accessControlManager;
    private String employeeId;

    public LogoutCommand(AccessControlManager accessControlManager, String employeeId) {
        this.accessControlManager = accessControlManager;
        this.employeeId = employeeId;
    }

    @Override
    public void execute() {
        accessControlManager.logout(employeeId);
    }
}
