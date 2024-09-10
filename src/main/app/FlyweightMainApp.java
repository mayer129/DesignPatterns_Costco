package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Flyweight.*;

public class FlyweightMainApp extends Application {

    private ProductFactory productFactory;
    private VisitableStockManager stockManager = new VisitableStockManager();
    private TextArea logArea;

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Flyweight Pattern Demo");

        // Text area for logging actions
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(400);

        // Initialize ProductFactory with TextArea for logging
        productFactory = new ProductFactory(logArea);

        // Buttons for triggering Flyweight and Visitor pattern actions
        Button addStockButton = new Button("Add Stock Items");
        Button generateReportButton = new Button("Generate Stock Report");
        Button applyDiscountButton = new Button("Apply Bulk Discount");

        // Event handler for adding stock items
        addStockButton.setOnAction(event -> {
            addStockItems();
        });

        // Event handler for generating stock report
        generateReportButton.setOnAction(event -> {
            generateStockReport();
        });

        // Event handler for applying bulk discount
        applyDiscountButton.setOnAction(event -> {
            applyBulkDiscount();
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(addStockButton, generateReportButton, applyDiscountButton, logArea);

        // Scene setup
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to add stock items using Flyweight pattern
    private void addStockItems() {
        // Add stock items to the stock manager
        stockManager.addStockItem(new StockItem("SKU123", 50, productFactory.getProductInfo("Apple iPhone 13", 999.99)));
        stockManager.addStockItem(new StockItem("SKU124", 20, productFactory.getProductInfo("Apple iPhone 13", 999.99)));  // Reuses ProductInfo
        stockManager.addStockItem(new StockItem("SKU125", 150, productFactory.getProductInfo("Samsung Galaxy S21", 799.99)));
        stockManager.addStockItem(new StockItem("SKU126", 5, productFactory.getProductInfo("Apple Watch", 399.99)));

        // Log the flyweight pattern result
        log("Total unique ProductInfo objects created (Flyweight pattern): " + productFactory.getTotalProductsCreated());
    }

    // Method to generate stock report using Visitor pattern
    private void generateStockReport() {
        ReportVisitor reportVisitor = new ReportVisitor(logArea);
        stockManager.accept(reportVisitor);
        reportVisitor.printReport();  // Prints report based on shared ProductInfo

        log("Stock report generated.");
    }

    // Method to apply bulk discount using Visitor pattern
    private void applyBulkDiscount() {
        DiscountVisitor discountVisitor = new DiscountVisitor(100, 0.10, logArea);  // 10% discount for bulk items
        stockManager.accept(discountVisitor);

        log("Bulk discount applied.");
    }

    // Method to log actions in the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
