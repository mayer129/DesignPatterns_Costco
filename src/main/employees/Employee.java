package employees;

public abstract class Employee {
    public String name;  // Change this to protected
    protected String employeeID;
    protected boolean accessGranted = false;

    public Employee(String name, String employeeID) {
        this.name = name;
        this.employeeID = employeeID;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;  // Getter method for name
    }

    public boolean isAccessGranted() {
        return accessGranted;
    }

    public void setAccessGranted(boolean accessGranted) {
        this.accessGranted = accessGranted;
    }

    public abstract void login();
    public abstract void logout();
}
