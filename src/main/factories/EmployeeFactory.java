package factories;

import employees.Employee;
import employees.Manager;
import employees.Cashier;
import access.EmployeeIDGenerator;
import javafx.scene.control.TextArea;

public class EmployeeFactory {

    public static Employee createEmployee(String type, String name, TextArea logArea) {
        String employeeID = EmployeeIDGenerator.generateUniqueID(name);  // Use the new ID generator
        switch (type.toLowerCase()) {
            case "cashier":
                return new Cashier(name, employeeID, logArea);
            case "manager":
                return new Manager(name, employeeID);
            default:
                throw new IllegalArgumentException("Unknown employee type");
        }
    }
}
