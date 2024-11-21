package observers;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ReceiptSystem implements Observer {

    private TextArea logArea;  // Reference to the TextArea for logging

    // Constructor to accept the TextArea
    public ReceiptSystem(TextArea logArea) {
        this.logArea = logArea;
    }

    @Override
    public void update(String itemName) {
        log("ReceiptSystem: Logging " + itemName + " for the receipt.");
        // Logic to log item for receipt generation goes here
    }

    // Method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
