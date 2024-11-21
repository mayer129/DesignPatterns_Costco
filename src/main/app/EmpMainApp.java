package app;

import employees.Cashier;
import employees.Employee;
import employees.Manager;
import factories.EmployeeFactory;
import access.AccessControlManager;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import payment.PaymentProcessorPool;
import observers.BillingSystem;
import observers.InventorySystem;
import observers.ReceiptSystem;

import java.util.HashMap;
import java.util.Map;

public class EmpMainApp extends Application {

    private static Map<String, Employee> employeeMap = new HashMap<>();
    private static AccessControlManager accessControlManager = AccessControlManager.getInstance();
    TextArea logArea = new TextArea();
    // Hardcoded manager and cashier
    private static final String HARDCODED_MANAGER_ID = "ADM001";
    private static final String HARDCODED_MANAGER_NAME = "Admin";
    private static final String HARDCODED_CASHIER_ID = "CAS001";
    private static final String HARDCODED_CASHIER_NAME = "Cashier";

    public static void main(String[] args) {
        launch(args); // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // Add hardcoded employees
        Employee admin = new Manager(HARDCODED_MANAGER_NAME, HARDCODED_MANAGER_ID);
        accessControlManager.grantAccess(admin);
        employeeMap.put(admin.getEmployeeID(), admin);

        Employee cashier = new Cashier(HARDCODED_CASHIER_NAME, HARDCODED_CASHIER_ID, logArea);

        accessControlManager.grantAccess(cashier);
        employeeMap.put(cashier.getEmployeeID(), cashier);

        // Main layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Title
        Label titleLabel = new Label("--- Employee Management System ---");

        // Manager and Cashier login buttons
        Button managerLoginBtn = new Button("Manager Login");
        Button cashierLoginBtn = new Button("Cashier Login");

        layout.getChildren().addAll(titleLabel, managerLoginBtn, cashierLoginBtn);

        // Set up scene
        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Employee Management System");
        primaryStage.show();

        // Event handlers for login buttons
        managerLoginBtn.setOnAction(event -> managerLogin(primaryStage));
        cashierLoginBtn.setOnAction(event -> cashierLogin(primaryStage));
    }

    // Method to handle manager login and manager operations
    private void managerLogin(Stage stage) {
        VBox managerLayout = new VBox(10);
        managerLayout.setPadding(new Insets(20));

        Label managerLoginLabel = new Label("Manager Login (ID: " + HARDCODED_MANAGER_ID + ")");
        Label managerNameLabel = new Label("Enter Manager ID:");
        TextField managerIDField = new TextField();
        Button loginBtn = new Button("Login");

        managerLayout.getChildren().addAll(managerLoginLabel, managerNameLabel, managerIDField, loginBtn);

        Scene managerScene = new Scene(managerLayout, 400, 300);
        stage.setScene(managerScene);

        loginBtn.setOnAction(event -> {
            String enteredID = managerIDField.getText();
            if (enteredID.equals(HARDCODED_MANAGER_ID)) {
                managerActions(stage);
            } else {
                showAlert("Login Failed", "Invalid Manager ID");
            }
        });
    }

    // Manager actions (Add/Remove Employees)
    private void managerActions(Stage stage) {
        VBox managerActionsLayout = new VBox(10);
        managerActionsLayout.setPadding(new Insets(20));

        Label managerActionsLabel = new Label("--- Manager Actions ---");

        Button addEmployeeBtn = new Button("Add Employee");
        Button removeEmployeeBtn = new Button("Remove Employee");
        Button logoutBtn = new Button("Logout");

        managerActionsLayout.getChildren().addAll(managerActionsLabel, addEmployeeBtn, removeEmployeeBtn, logoutBtn);

        Scene managerActionsScene = new Scene(managerActionsLayout, 400, 300);
        stage.setScene(managerActionsScene);

        // Event handlers for manager actions
        addEmployeeBtn.setOnAction(event -> addEmployee(stage));
        removeEmployeeBtn.setOnAction(event -> removeEmployee(stage));
        logoutBtn.setOnAction(event -> start(stage));  // Go back to main screen
    }

    // Add employee method (UI for adding an employee)
    private void addEmployee(Stage stage) {
        VBox addEmployeeLayout = new VBox(10);
        addEmployeeLayout.setPadding(new Insets(20));

        Label employeeTypeLabel = new Label("Employee Type (cashier/manager):");
        ComboBox<String> employeeTypeBox = new ComboBox<>();
        employeeTypeBox.getItems().addAll("cashier", "manager");

        Label employeeNameLabel = new Label("Employee Name:");
        TextField employeeNameField = new TextField();

        Button addBtn = new Button("Add Employee");

        addEmployeeLayout.getChildren().addAll(employeeTypeLabel, employeeTypeBox, employeeNameLabel, employeeNameField, addBtn);

        Scene addEmployeeScene = new Scene(addEmployeeLayout, 400, 300);
        stage.setScene(addEmployeeScene);

        addBtn.setOnAction(event -> {
            String type = employeeTypeBox.getValue();
            String name = employeeNameField.getText();

            try {
                Employee employee = EmployeeFactory.createEmployee(type, name, logArea);
                employeeMap.put(employee.getEmployeeID(), employee);
                accessControlManager.grantAccess(employee);
                showAlert("Success", type + " " + name + " added with ID: " + employee.getEmployeeID());
                managerActions(stage);
            } catch (IllegalArgumentException e) {
                showAlert("Error", e.getMessage());
            }
        });
    }

    // Remove employee method
    private void removeEmployee(Stage stage) {
        VBox removeEmployeeLayout = new VBox(10);
        removeEmployeeLayout.setPadding(new Insets(20));

        Label removeEmployeeLabel = new Label("Enter Employee ID to remove:");
        TextField removeEmployeeField = new TextField();
        Button removeBtn = new Button("Remove Employee");

        removeEmployeeLayout.getChildren().addAll(removeEmployeeLabel, removeEmployeeField, removeBtn);

        Scene removeEmployeeScene = new Scene(removeEmployeeLayout, 400, 300);
        stage.setScene(removeEmployeeScene);

        removeBtn.setOnAction(event -> {
            String employeeID = removeEmployeeField.getText();
            if (employeeMap.containsKey(employeeID)) {
                Employee removedEmployee = employeeMap.remove(employeeID);
                accessControlManager.revokeAccess(removedEmployee);
                showAlert("Success", "Employee " + removedEmployee.getName() + " removed.");
                managerActions(stage);
            } else {
                showAlert("Error", "Employee not found with ID: " + employeeID);
            }
        });
    }

    // Cashier login and actions
    private void cashierLogin(Stage stage) {
        VBox cashierLayout = new VBox(10);
        cashierLayout.setPadding(new Insets(20));

        Label cashierLoginLabel = new Label("Cashier Login (ID: " + HARDCODED_CASHIER_ID + ")");
        Label cashierIDLabel = new Label("Enter Cashier ID:");
        TextField cashierIDField = new TextField();
        Button loginBtn = new Button("Login");

        cashierLayout.getChildren().addAll(cashierLoginLabel, cashierIDLabel, cashierIDField, loginBtn);

        Scene cashierScene = new Scene(cashierLayout, 400, 300);
        stage.setScene(cashierScene);

        loginBtn.setOnAction(event -> {
            String enteredID = cashierIDField.getText();
            Employee employee = employeeMap.get(enteredID);

            if (employee instanceof Cashier && employee.isAccessGranted()) {
                cashierActions(stage, (Cashier) employee);
            } else {
                showAlert("Login Failed", "Invalid Cashier ID or Access Denied");
            }
        });
    }

    // Cashier actions (Scan Items, Continue to Payment)
    private void cashierActions(Stage stage, Cashier cashier) {
        VBox cashierActionsLayout = new VBox(10);
        cashierActionsLayout.setPadding(new Insets(20));

        Label cashierActionsLabel = new Label("--- Cashier Actions ---");

        // Initialize observers (BillingSystem, InventorySystem, ReceiptSystem) with the same logArea
        BillingSystem billingSystem = new BillingSystem(logArea);
        InventorySystem inventorySystem = new InventorySystem(logArea);
        ReceiptSystem receiptSystem = new ReceiptSystem(logArea);

        // Register observers with the cashier
        cashier.registerObserver(billingSystem);
        cashier.registerObserver(inventorySystem);
        cashier.registerObserver(receiptSystem);

        // Buttons for cashier actions
        Button scanItemBtn = new Button("Scan Items");
        Button paymentBtn = new Button("Proceed to Payment");
        Button logoutBtn = new Button("Logout");

        cashierActionsLayout.getChildren().addAll(cashierActionsLabel, scanItemBtn, paymentBtn, logoutBtn, logArea);

        Scene cashierActionsScene = new Scene(cashierActionsLayout, 400, 300);
        stage.setScene(cashierActionsScene);

        // Event handlers for cashier actions
        scanItemBtn.setOnAction(event -> scanItem(cashier));
        paymentBtn.setOnAction(event -> proceedToPayment(cashier));  // Use logArea from the class-level
        logoutBtn.setOnAction(event -> start(stage));  // Go back to the main screen
    }






    // Method to scan an item
    private void scanItem(Cashier cashier) {
        VBox scanItemLayout = new VBox(10);
        scanItemLayout.setPadding(new Insets(20));

        Label scanItemLabel = new Label("Enter item name to scan:");
        TextField itemNameField = new TextField();
        Button scanBtn = new Button("Scan Item");

        scanItemLayout.getChildren().addAll(scanItemLabel, itemNameField, scanBtn);

        Stage scanStage = new Stage();
        Scene scanItemScene = new Scene(scanItemLayout, 400, 200);
        scanStage.setScene(scanItemScene);
        scanStage.show();

        scanBtn.setOnAction(event -> {
            String itemName = itemNameField.getText();
            cashier.scanItem(itemName);  // Notify all observers
            scanStage.close();
        });
    }

    // Method to proceed to payment
    // Method to proceed to payment in the cashierActions method
    private void proceedToPayment(Cashier cashier) {
        VBox paymentLayout = new VBox(10);
        paymentLayout.setPadding(new Insets(20));

        Label paymentLabel = new Label("Proceeding to Payment");
        Label amountLabel = new Label("Amount: $100.50");
        Label customerLabel = new Label("Customer Name: Customer");
        Label cardLabel = new Label("Card: Visa ****1234");
        Button proceedPaymentBtn = new Button("Confirm Payment");

        paymentLayout.getChildren().addAll(paymentLabel, amountLabel, customerLabel, cardLabel, proceedPaymentBtn);

        Scene paymentScene = new Scene(paymentLayout, 400, 300);
        Stage paymentStage = new Stage();
        paymentStage.setScene(paymentScene);
        paymentStage.show();

        proceedPaymentBtn.setOnAction(event -> {
            // Initialize PaymentProcessorPool with logArea
            PaymentProcessorPool pool = PaymentProcessorPool.getInstance(5, logArea);

            // Handle payment and log the pool actions
            cashier.handlePayment(pool, 100.50, "Customer", "Visa ****1234");

            paymentStage.close();
        });
    }


    // Utility method to show alert messages
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
