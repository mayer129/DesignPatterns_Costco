package app;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerSupportMainApp extends Application {

    private SupportHandler level1Support;
    private TextArea logArea;

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Customer Support System");

        // Initialize the support chain
        level1Support = new Level1Support();
        SupportHandler level2Support = new Level2Support();
        SupportHandler level3Support = new Level3Support();

        level1Support.setNextHandler(level2Support);
        level2Support.setNextHandler(level3Support);

        // UI components
        logArea = new TextArea();
        logArea.setEditable(false);  // Make the log area read-only

        Button processBasicBtn = new Button("Process Basic Request");
        Button processIntermediateBtn = new Button("Process Intermediate Request");
        Button processAdvancedBtn = new Button("Process Advanced Request");
        Button exitBtn = new Button("Exit");

        // VBox layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(processBasicBtn, processIntermediateBtn, processAdvancedBtn, logArea, exitBtn);

        // Button actions
        processBasicBtn.setOnAction(event -> processBasicRequest());
        processIntermediateBtn.setOnAction(event -> processIntermediateRequest());
        processAdvancedBtn.setOnAction(event -> processAdvancedRequest());
        exitBtn.setOnAction(event -> Platform.exit());

        // Set the scene and show the stage
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to process a basic request
    private void processBasicRequest() {
        SupportRequest basicRequest = new SupportRequest("basic", "Password reset.");
        logArea.appendText("Processing Basic Request:\n");
        level1Support.handleRequest(basicRequest, logArea);  // Pass the logArea here
    }

    // Method to process an intermediate request
    private void processIntermediateRequest() {
        SupportRequest intermediateRequest = new SupportRequest("intermediate", "Issue with billing.");
        logArea.appendText("\nProcessing Intermediate Request:\n");
        level1Support.handleRequest(intermediateRequest, logArea);  // Pass the logArea here
    }

    // Method to process an advanced request
    private void processAdvancedRequest() {
        SupportRequest advancedRequest = new SupportRequest("advanced", "Technical issue with online orders.");
        logArea.appendText("\nProcessing Advanced Request:\n");
        level1Support.handleRequest(advancedRequest, logArea);  // Pass the logArea here
    }
}
