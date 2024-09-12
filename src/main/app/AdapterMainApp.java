package main.app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import main.Adapter.Tesla1Charger;
import main.Adapter.Tesla1ToTeslaAdapter;
import main.Adapter.TeslaSuperCharger;
import main.Adapter.UniversalCharger;

public class AdapterMainApp extends Application {

    private TextArea logArea;

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tesla Charging System (Adapter Pattern)");

        // Initialize the log area
        logArea = new TextArea();
        logArea.setEditable(false);  // Make the log area read-only
        logArea.setPrefHeight(300);

        // Create buttons for different charging operations
        Button teslaSuperChargerBtn = new Button("Charge with Tesla SuperCharger");
        Button tesla1ChargerBtn = new Button("Charge Tesla with Tesla1 Adapter");
        Button exitBtn = new Button("Exit");

        // VBox layout for the buttons and log area
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(teslaSuperChargerBtn, tesla1ChargerBtn, logArea, exitBtn);

        // Button actions
        teslaSuperChargerBtn.setOnAction(event -> chargeWithTeslaSuperCharger());
        tesla1ChargerBtn.setOnAction(event -> chargeWithTesla1Adapter());
        exitBtn.setOnAction(event -> Platform.exit());

        // Set the scene and show the stage
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to handle Tesla SuperCharger operation
    private void chargeWithTeslaSuperCharger() {
        TeslaSuperCharger teslaCharger = new TeslaSuperCharger(logArea);
        teslaCharger.chargeWithTeslaSuperCharger();
    }

    // Method to handle Tesla1 Charger with adapter operation
    private void chargeWithTesla1Adapter() {
        Tesla1Charger oldCharger = new Tesla1Charger(logArea);
        UniversalCharger adapter = new Tesla1ToTeslaAdapter(oldCharger, logArea);
        adapter.charge();
    }
}
