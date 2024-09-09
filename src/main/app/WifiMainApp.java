package app;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import wifi.WiFiAccess;
import wifi.WiFiProxy;

public class WifiMainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create the WiFi Proxy
        WiFiAccess wifiProxy = new WiFiProxy();

        // Create the UI components
        Label nameLabel = new Label("Enter your name:");
        TextField nameField = new TextField();
        CheckBox agreeCheckBox = new CheckBox("I agree to the store's data collection policy.");
        Button connectButton = new Button("Request Wi-Fi Access");
        Label resultLabel = new Label();

        // Handle button click event
        connectButton.setOnAction(event -> {
            String customerName = nameField.getText();
            boolean agreedToPolicy = agreeCheckBox.isSelected();  // Check the checkbox status

            // Only grant access if the checkbox is selected
            if (agreedToPolicy) {
                wifiProxy.grantAccess(customerName);  // Call the WiFiProxy to grant access
                resultLabel.setText("Wi-Fi access granted to " + customerName + ".");
            } else {
                resultLabel.setText("Access denied. You must agree to the data collection policy.");
            }
        });

        // Set up the layout
        VBox layout = new VBox(10, nameLabel, nameField, agreeCheckBox, connectButton, resultLabel);
        layout.setPadding(new Insets(20));

        // Create and set the scene
        Scene scene = new Scene(layout, 400, 250);
        primaryStage.setTitle("Wi-Fi Access System");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
