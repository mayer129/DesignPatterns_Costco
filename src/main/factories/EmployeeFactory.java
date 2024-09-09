import employees.Employee;
import employees.Manager;
import employees.Cashier;
import access.EmployeeIDGenerator;

public class EmployeeFactory {

    public static Employee createEmployee(String type, String name) {
        String employeeID = EmployeeIDGenerator.generateUniqueID(name);  // Use the new ID generator
        switch (type.toLowerCase()) {
            case "cashier":
                return new Cashier(name, employeeID);
            case "manager":
                return new Manager(name, employeeID);
            default:
                throw new IllegalArgumentException("Unknown employee type");
        }
    }
}
