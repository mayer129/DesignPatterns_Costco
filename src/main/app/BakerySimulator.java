package main.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Bakery.*;

public class BakerySimulator extends Application {

    private static BakeryMediator mediator;
    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        // Initialize log area for logging actions
        logArea = new TextArea();
        logArea.setEditable(false);  // Make the log area read-only
        logArea.setPrefHeight(400);

        Label titleLabel = new Label("Welcome to the Costco Bakery!");

        // Initialize processor and interpreter with logArea
        OrderProcessor processor = new CustomCakeOrderProcessor(logArea);
        OrderInterpreter interpreter = new OrderInterpreter();
        mediator = new BakeryMediator(processor, interpreter, logArea);  // Pass logArea to the mediator

        // Buttons for placing orders, processing orders, and exiting
        Button placeOrderBtn = new Button("Place an Order");
        Button processOrderBtn = new Button("Process Orders");
        Button exitBtn = new Button("Exit");

        // Layout for UI elements
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, placeOrderBtn, processOrderBtn, logArea, exitBtn);

        // Event handling for buttons
        placeOrderBtn.setOnAction(event -> placeOrderUI());
        processOrderBtn.setOnAction(event -> processOrdersUI());
        exitBtn.setOnAction(event -> {
            log("Thank you for visiting the Costco Bakery. Goodbye!");
            Platform.exit();  // Exit the application
        });

        // Set up the scene and stage
        Scene scene = new Scene(layout, 500, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Costco Bakery Simulator");
        primaryStage.show();
    }

    // Method to handle placing an order via the UI
    private void placeOrderUI() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Place an Order");
        dialog.setHeaderText("Enter your cake order (e.g., '1 red chocolate cake, 2 white vanilla cakes')");
        dialog.setContentText("Order:");

        dialog.showAndWait().ifPresent(orderInput -> {
            log("\n--- Placing Order ---");
            mediator.placeOrder(orderInput);
        });
    }

    // Method to process orders via the UI
    private void processOrdersUI() {
        log("\n--- Processing Orders ---");
        mediator.processOrders();
    }

    // Utility method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
