package app;

import composite.ProductCategory;
import composite.ProductComponent;
import composite.ProductItem;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CompositeMainApp extends Application {

    private TextArea logArea;

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize log area
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(300);

        // Create buttons for displaying categories and calculating total prices
        Button displayCategoriesBtn = new Button("Display Categories");
        Button calculateTotalPriceBtn = new Button("Calculate Total Price");

        // Create individual product items for groceries
        ProductComponent apples = new ProductItem("Apples", 2.99);
        ProductComponent milk = new ProductItem("Milk", 1.49);
        ProductComponent bread = new ProductItem("Bread", 2.49);

        // Create a groceries category and add products to it
        ProductComponent groceriesCategory = new ProductCategory("Groceries");
        groceriesCategory.add(apples);
        groceriesCategory.add(milk);
        groceriesCategory.add(bread);

        // Create individual product items for toiletries
        ProductComponent shampoo = new ProductItem("Shampoo", 5.99);
        ProductComponent soap = new ProductItem("Soap", 3.99);

        // Create a toiletries category and add products to it
        ProductComponent toiletriesCategory = new ProductCategory("Toiletries");
        toiletriesCategory.add(shampoo);
        toiletriesCategory.add(soap);

        // Create a main Costco category to hold both groceries and toiletries
        ProductComponent costcoCategory = new ProductCategory("Costco");
        costcoCategory.add(groceriesCategory);
        costcoCategory.add(toiletriesCategory);

        // Event handler for displaying categories
        displayCategoriesBtn.setOnAction(event -> {
            log("\n--- Displaying Costco Categories ---");
            costcoCategory.displayInfo(logArea);
        });

        // Event handler for calculating total price
        calculateTotalPriceBtn.setOnAction(event -> {
            double totalPrice = costcoCategory.getTotalPrice();
            log("\nTotal price of all products: $" + totalPrice);
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(displayCategoriesBtn, calculateTotalPriceBtn, logArea);

        // Set up scene and stage
        Scene scene = new Scene(layout, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Costco Product Catalog");
        primaryStage.show();
    }

    // Utility method for logging
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
