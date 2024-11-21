package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import security.WalkieTalkieHub;
import security.SecurityPersonnel;
import security.Guard;
import security.Supervisor;
import strategy.StandardResponse;
import strategy.LockdownResponse;
import strategy.EmergencyResponse;
import strategy.ResponseStrategy;

public class SecurityMainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a WalkieTalkieHub (Mediator) with TextArea for logging
        TextArea logArea = new TextArea();
        logArea.setEditable(false);  // Disable text editing for log area
        logArea.setWrapText(true);   // Enable wrapping for long logs
        logArea.setPrefHeight(300);  // Set preferred height for the log area
        logArea.setPrefWidth(450);   // Set preferred width for the log area

        WalkieTalkieHub hub = new WalkieTalkieHub(logArea);

        // Create initial response strategies
        ResponseStrategy standardResponse = new StandardResponse();
        ResponseStrategy emergencyResponse = new EmergencyResponse();
        ResponseStrategy lockdownResponse = new LockdownResponse();

        // Create security personnel
        SecurityPersonnel guard1 = new Guard("Sebastian", hub);
        SecurityPersonnel guard2 = new Guard("Travis", hub);
        SecurityPersonnel guard3 = new Guard("Rebecca", hub);
        SecurityPersonnel supervisor1 = new Supervisor("Prof. Navid", hub);

        // Register personnel to the hub
        hub.registerSecurity(guard1);
        hub.registerSecurity(guard2);
        hub.registerSecurity(guard3);
        hub.registerSecurity(supervisor1);

        // Create the UI components
        Label messageLabel = new Label("Enter the message:");
        TextField messageField = new TextField();

        Label strategyLabel = new Label("Select Alert Level:");
        ComboBox<String> strategyBox = new ComboBox<>();
        strategyBox.getItems().addAll("Standard", "Emergency", "Lockdown");

        // ComboBox for selecting the origin (sender)
        Label originLabel = new Label("Select Origin (Sender):");
        ComboBox<SecurityPersonnel> originBox = new ComboBox<>();
        originBox.getItems().addAll(hub.getAllPersonnel());

        // ComboBox for selecting the destination (recipient)
        Label destinationLabel = new Label("Select Destination (Recipient):");
        ComboBox<String> destinationBox = new ComboBox<>();
        destinationBox.getItems().add("Broadcast");  // Add the broadcast option
        for (SecurityPersonnel personnel : hub.getAllPersonnel()) {
            destinationBox.getItems().add(personnel.getName());  // Add all personnel
        }

        // Button to handle sending messages
        Button sendButton = new Button("Send Message");

        // Handle button click event
        sendButton.setOnAction(event -> {
            String message = messageField.getText();
            SecurityPersonnel origin = originBox.getValue();
            String destination = destinationBox.getValue();
            String selectedStrategy = strategyBox.getValue();

            // Set the response strategy for the origin personnel
            ResponseStrategy responseStrategy = new StandardResponse();  // Default to Standard Response
            if ("Emergency".equals(selectedStrategy)) {
                responseStrategy = new EmergencyResponse();
            } else if ("Lockdown".equals(selectedStrategy)) {
                responseStrategy = new LockdownResponse();
            }

            // Apply the selected strategy to the origin personnel
            origin.setResponseStrategy(responseStrategy);

            // Check if it's a broadcast or private message
            if ("Broadcast".equals(destination)) {
                // Broadcast to everyone except the origin
                origin.broadcast(message);
            } else {
                // Find the destination personnel by name
                SecurityPersonnel recipient = hub.getAllPersonnel().stream()
                        .filter(person -> person.getName().equals(destination))
                        .findFirst()
                        .orElse(null);

                if (recipient != null) {
                    // Private message from origin to the selected destination
                    origin.sendPrivateMessage(message, recipient);
                }
            }

            // Scroll to the bottom of the log when a new message is added
            logArea.appendText("");  // Trigger append to handle the scroll
            logArea.setScrollTop(Double.MAX_VALUE);  // Ensure scroll goes to the bottom
        });

        // Set up the layout
        VBox layout = new VBox(10, messageLabel, messageField, strategyLabel, strategyBox, originLabel, originBox, destinationLabel, destinationBox, sendButton, logArea);
        layout.setPadding(new Insets(20));

        // Create and set the scene
        Scene scene = new Scene(layout, 500, 500);
        primaryStage.setTitle("Security Communication System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
