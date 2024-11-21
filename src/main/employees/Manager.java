package employees;

public class Manager extends Employee {
    public Manager(String name, String employeeID) {
        super(name, employeeID);
    }

    @Override
    public void login() {
        if (accessGranted) {
            System.out.println("Manager " + name + " logged in with ID: " + employeeID);
        } else {
            System.out.println("Access denied for Manager " + name);
        }
    }

    @Override
    public void logout() {
        System.out.println("Manager " + name + " logged out.");
    }
}
