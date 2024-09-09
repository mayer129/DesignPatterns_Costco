
import employees.Cashier;
import employees.Employee;
import employees.Manager;
import payment.PaymentProcessorPool;


import access.AccessControlManager;
import observers.BillingSystem;
import observers.InventorySystem;
import observers.ReceiptSystem;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EmpMainApp {

    private static Map<String, Employee> employeeMap = new HashMap<>();
    private static AccessControlManager accessControlManager = AccessControlManager.getInstance();
    private static Scanner scanner = new Scanner(System.in);

    // Hardcoded manager
    private static final String HARDCODED_MANAGER_NAME = "Admin";
    private static final String HARDCODED_MANAGER_ID = "ADM001";

    private static final String HARDCODED_CASHIER_NAME = "Cashier";
    private static final String HARDCODED_CASHIER_ID = "CAS001";

    public static void main(String[] args) {
        // Step 1: Add the hardcoded admin/manager
        Employee admin = new Manager(HARDCODED_MANAGER_NAME, HARDCODED_MANAGER_ID);
        accessControlManager.grantAccess(admin);
        employeeMap.put(admin.getEmployeeID(), admin);
        System.out.println("Hardcoded manager added with ID: " + HARDCODED_MANAGER_ID);

        Employee cashier = new Cashier(HARDCODED_CASHIER_NAME, HARDCODED_CASHIER_ID);
        accessControlManager.grantAccess(cashier);
        employeeMap.put(cashier.getEmployeeID(), cashier);
        System.out.println("Hardcoded cashier added with ID: " + HARDCODED_CASHIER_ID);

        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Manager Login");
            System.out.println("2. Cashier Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    managerLogin();
                    break;
                case 2:
                    cashierLogin();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle manager login
    private static void managerLogin() {
        System.out.print("Enter Manager ID: ");
        String managerID = scanner.nextLine();

        if (managerID.equals(HARDCODED_MANAGER_ID)) {
            System.out.println("Admin logged in.");
            manageEmployees();
        } else {
            Employee employee = employeeMap.get(managerID);
            if (employee instanceof Manager && employee.isAccessGranted()) {
                System.out.println("Manager " + employee.name + " logged in.");
                manageEmployees();
            } else {
                System.out.println("Invalid Manager credentials or access denied.");
            }
        }
    }

    // Manager functionality to add and remove employees
    private static void manageEmployees() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Admin Section ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Remove Employee");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    removeEmployee();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to add an employee
    private static void addEmployee() {
        System.out.print("Enter employee type (cashier/manager): ");
        String type = scanner.nextLine().toLowerCase();

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        try {
            Employee employee = EmployeeFactory.createEmployee(type, name);
            employeeMap.put(employee.getEmployeeID(), employee);
            
            // Automatically grant access upon creation
            accessControlManager.grantAccess(employee);
            
            System.out.println(type.substring(0, 1).toUpperCase() + type.substring(1) + " " + name + " added with ID: " + employee.getEmployeeID());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Method to remove an employee by ID
    private static void removeEmployee() {
        System.out.print("Enter employee ID to remove: ");
        String employeeID = scanner.nextLine();

        if (employeeMap.containsKey(employeeID)) {
            Employee removedEmployee = employeeMap.remove(employeeID);
            accessControlManager.revokeAccess(removedEmployee);
            System.out.println("Employee " + removedEmployee.name + " removed and access revoked.");
        } else {
            System.out.println("Employee not found with ID: " + employeeID);
        }
    }

    // Method to handle cashier login
    private static void cashierLogin() {
        System.out.print("Enter Cashier ID: ");
        String cashierID = scanner.nextLine();

        Employee employee = employeeMap.get(cashierID);

        if (employee instanceof Cashier && employee.isAccessGranted()) {
            System.out.println("Cashier " + employee.name + " logged in.");

            // Attach observers (Inventory, Billing, Receipt) to the cashier
            InventorySystem inventorySystem = new InventorySystem();
            BillingSystem billingSystem = new BillingSystem();
            ReceiptSystem receiptSystem = new ReceiptSystem();

            Cashier cashier = (Cashier) employee;
            cashier.registerObserver(inventorySystem);
            cashier.registerObserver(billingSystem);
            cashier.registerObserver(receiptSystem);

            cashierActions(cashier);
        } else {
            System.out.println("Invalid Cashier credentials or access denied.");
        }
    }

    // Cashier-specific actions
    private static void cashierActions(Cashier cashier) {
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Cashier Section ---");
            System.out.println("1. Scan Items");
            System.out.println("2. Continue to Payment");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter item to scan: ");
                    String itemName = scanner.nextLine();
                    cashier.scanItem(itemName); // Notify all observers
                    break;
                case 2:
                    //System.out.println("Proceeding to payment...");
                    PaymentProcessorPool pool = PaymentProcessorPool.getInstance(5);
        
                    // Simulate cashier clicking "Proceed to Payment"
                    
                    cashier.handlePayment(pool, 100.50, "John Doe", "Visa ****1234"); // Existing method in Cashier class
                    break;
                case 3:
                    exit = true;
                    cashier.logout(); // Existing logout method in Cashier class
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
