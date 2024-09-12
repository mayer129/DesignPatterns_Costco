package command;

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

    public void login(String employeeId, String password) {
        // Logic to validate employeeId and password, then grant access
        if (validateCredentials(employeeId, password)) {
            employeeAccessMap.put(employeeId, true);
            System.out.println("Employee " + employeeId + " logged in.");
        } else {
            System.out.println("Invalid login attempt for employee " + employeeId);
        }
    }

    public void logout(String employeeId) {
        employeeAccessMap.put(employeeId, false);
        System.out.println("Employee " + employeeId + " logged out.");
    }

    private boolean validateCredentials(String employeeId, String password) {
        // Replace with real credential validation logic
        return "password123".equals(password);  // Just for demonstration
    }
}
