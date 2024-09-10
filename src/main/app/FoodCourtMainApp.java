package app;

import java.util.Scanner;

import foodcourt.FoodCourtFactory;
import foodcourt.FoodItem;
import foodcourt.OrderFoodCommand;
import foodcourt.Soda;
import foodcourt.CondimentKetchup;
import foodcourt.CondimentMustard;
import foodcourt.CondimentRelish;
import foodcourt.FillSodaCommand;



import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FoodCourtMainApp extends Application {

    private FoodCourtFactory factory = new FoodCourtFactory();
    private TextArea logArea;  // To log actions
    private FoodItem currentOrder;
    private Soda currentSoda;

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Costco Food Court");

        // Text area to log actions
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(300);

        // Buttons and input fields
        Label welcomeLabel = new Label("Welcome to the Costco Food Court!");

        // Food options (ComboBox)
        ComboBox<String> foodComboBox = new ComboBox<>();
        foodComboBox.getItems().addAll("Hotdog", "Cheese Pizza", "Pepperoni Pizza");
        foodComboBox.setPromptText("Select Food");

        // Condiment options (CheckBoxes)
        CheckBox ketchupBox = new CheckBox("Ketchup");
        CheckBox mustardBox = new CheckBox("Mustard");
        CheckBox relishBox = new CheckBox("Relish");

        // Soda options (ComboBox)
        ComboBox<String> sodaComboBox = new ComboBox<>();
        sodaComboBox.getItems().addAll("Coca-Cola", "Sprite", "Fanta");
        sodaComboBox.setPromptText("Select Soda");

        // Order button
        Button orderButton = new Button("Place Order");

        // Event handler for placing an order
        orderButton.setOnAction(event -> {
            placeOrder(foodComboBox, ketchupBox, mustardBox, relishBox, sodaComboBox);
        });

        // Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(
                welcomeLabel, foodComboBox,
                ketchupBox, mustardBox, relishBox,
                sodaComboBox, orderButton, logArea
        );

        // Scene
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to handle the order
    // Method to handle the order
    private void placeOrder(ComboBox<String> foodComboBox, CheckBox ketchupBox, CheckBox mustardBox, CheckBox relishBox, ComboBox<String> sodaComboBox) {
        // Log and handle food selection
        String selectedFood = foodComboBox.getValue();
        if (selectedFood == null) {
            log("No food selected. Please choose an item.");
            return;
        }
        currentOrder = factory.createFoodItem(selectedFood.toLowerCase());
        log("Food ordered: " + selectedFood);

        // Log and handle condiments
        if (ketchupBox.isSelected()) {
            currentOrder = new CondimentKetchup(currentOrder);
            log("Added condiment: Ketchup");
        }
        if (mustardBox.isSelected()) {
            currentOrder = new CondimentMustard(currentOrder);
            log("Added condiment: Mustard");
        }
        if (relishBox.isSelected()) {
            currentOrder = new CondimentRelish(currentOrder);
            log("Added condiment: Relish");
        }

        // Log and handle soda selection
        String selectedSoda = sodaComboBox.getValue();
        if (selectedSoda == null) {
            log("No soda selected. Please choose a soda.");
            return;
        }
        currentSoda = factory.createSoda(selectedSoda.toLowerCase());
        log("Soda ordered: " + selectedSoda);

        // Execute the order
        OrderFoodCommand orderCommand = new OrderFoodCommand(currentOrder, currentSoda, logArea);  // Pass logArea as the third argument
        orderCommand.execute();

        // Fill the soda as a separate command
        FillSodaCommand fillSodaCommand = new FillSodaCommand(currentSoda, logArea);  // Pass logArea for logging
        fillSodaCommand.execute();

        log("Order placed successfully!");
    }


    // Method to log actions in the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
