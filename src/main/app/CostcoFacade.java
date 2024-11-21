package app;

import javafx.stage.Stage;

public class CostcoFacade {

    // Method to open the Bakery Department
    public void openBakery(Stage stage) {
        try {
            BakerySimulator bakeryApp = new BakerySimulator();
            bakeryApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the Customer Support Department
    public void openSupport(Stage stage) {
        try {
            CustomerSupportMainApp supportApp = new CustomerSupportMainApp();
            supportApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the Security Department
    public void openSecurity(Stage stage) {
        try {
            SecurityMainApp securityApp = new SecurityMainApp();
            securityApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the Employee Management Department
    public void openEmployeeManagement(Stage stage) {
        try {
            EmpMainApp empManageApp = new EmpMainApp();
            empManageApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the WiFi Access Department
    public void openWiFi(Stage stage) {
        try {
            WifiMainApp wifiApp = new WifiMainApp();
            wifiApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the Product Catalog (Composite Main App)
    public void openCompositeCatalog(Stage stage) {
        try {
            CompositeMainApp compositeApp = new CompositeMainApp();
            compositeApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the Food Court
    public void openFoodCourt(Stage stage) {
        try {
            FoodCourtMainApp foodCourtApp = new FoodCourtMainApp();
            foodCourtApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the Flyweight Demo
    public void openFlyweightDemo(Stage stage) {
        try {
            FlyweightMainApp flyweightApp = new FlyweightMainApp();
            flyweightApp.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to open the Iterator Demo
    public void openIteratorDemo(Stage stage) {
        try {
            IteratorMainApp iteratorApp = new IteratorMainApp();
            iteratorApp.start(stage);  // Use the IteratorMainApp's start method
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
