package observers;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class BillingSystem implements Observer {

    private TextArea logArea;  // Reference to the TextArea for logging

    // Constructor to accept the TextArea
    public BillingSystem(TextArea logArea) {
        this.logArea = logArea;
    }

    @Override
    public void update(String itemName) {
        log("PointsSystem: Adding " + 100 + " points to your account");
        // Logic to add item to the bill goes here
    }

    // Method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
