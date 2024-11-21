package access;

import employees.Employee;
import java.util.HashMap;
import java.util.Map;

public class AccessControlManager {
    private static AccessControlManager instance;
    private Map<String, Boolean> employeeAccessMap;

    private AccessControlManager() {
        employeeAccessMap = new HashMap<>();
    }

    public static AccessControlManager getInstance() {
        if (instance == null) {
            instance = new AccessControlManager();
        }
        return instance;
    }

    public void grantAccess(Employee employee) {
        employee.setAccessGranted(true);
        employeeAccessMap.put(employee.getEmployeeID(), true);
        System.out.println("Access granted to employee: " + employee.name);
    }

    public void revokeAccess(Employee employee) {
        employee.setAccessGranted(false);
        employeeAccessMap.put(employee.getEmployeeID(), false);
        System.out.println("Access revoked from employee: " + employee.name);
    }

    public boolean isAccessGranted(String employeeID) {
        return employeeAccessMap.getOrDefault(employeeID, false);
    }
}
