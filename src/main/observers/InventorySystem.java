package observers;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class InventorySystem implements Observer {

    private TextArea logArea;  // Reference to the TextArea for logging

    // Constructor to accept the TextArea
    public InventorySystem(TextArea logArea) {
        this.logArea = logArea;
    }

    @Override
    public void update(String itemName) {
        log("InventorySystem: Reducing stock for " + itemName);
        // Logic to reduce inventory stock goes here
    }

    // Method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
