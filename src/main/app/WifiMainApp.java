package main.wifi;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.wifi.WiFiProxy;

public class WifiMainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Costco Wi-Fi Access");

        Label nameLabel = new Label("Enter your name:");
        TextField nameField = new TextField();
        nameField.setPromptText("Customer Name");

        CheckBox agreementCheckBox = new CheckBox("I agree to the store's data collection policy.");

        Button accessButton = new Button("Request Wi-Fi Access");
        TextArea logArea = new TextArea();
        logArea.setEditable(false);  // Ensure the log area is not editable

        accessButton.setOnAction(event -> {
            String customerName = nameField.getText();
            boolean hasAgreed = agreementCheckBox.isSelected();  // Corrected case for agreementCheckBox

            WiFiProxy proxy = new WiFiProxy(hasAgreed, logArea);
            proxy.grantAccess(customerName);
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(nameLabel, nameField, agreementCheckBox, accessButton, logArea);

        Scene scene = new Scene(layout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
