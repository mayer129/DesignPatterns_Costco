package app;

import bakery.OrderProcessor;
import bakery.OrderInterpreter;
import bakery.Cake;
import bakery.Order;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class BakerySimulator extends Application {

    private OrderProcessor processor;  // Now we will initialize this later
    private static final OrderInterpreter interpreter = new OrderInterpreter();
    private TextArea logArea;

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        // Initialize log area for logging actions
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(400);

        // Initialize processor with logArea
        processor = new OrderProcessor(logArea);

        Label titleLabel = new Label("Welcome to the Costco Bakery!");

        // Buttons for placing orders, processing orders, and exiting
        Button placeOrderBtn = new Button("Place an Order");
        Button processOrderBtn = new Button("Process Orders");
        Button exitBtn = new Button("Exit");

        // Order input field
        TextField orderInputField = new TextField();
        orderInputField.setPromptText("Enter your cake order (e.g., '1 red chocolate cake, 2 white vanilla cakes')");

        // Layout for UI elements
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(titleLabel, orderInputField, placeOrderBtn, processOrderBtn, exitBtn, logArea);

        // Event handling for buttons
        placeOrderBtn.setOnAction(event -> {
            String orderInput = orderInputField.getText();
            placeOrder(orderInput);
            orderInputField.clear();  // Clear input after order is placed
        });

        processOrderBtn.setOnAction(event -> processOrders());

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

    // Method to place an order
    private void placeOrder(String orderInput) {
        log("\n--- Placing Order ---");  // Add spacing before the section

        List<Cake> cakes = interpreter.interpret(orderInput);

        if (cakes.isEmpty()) {
            log("Invalid order. Please try again.");
            return;
        }

        for (Cake cake : cakes) {
            Order order = new Order(cake);
            processor.addOrder(order);
            log("Order placed: " + order + " - Price: $15.00");
        }
    }

    // Method to process orders
    private void processOrders() {
        log("\n--- Processing Orders ---");  // Add spacing before the section
        processor.processOrders();
    }

    // Utility method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
