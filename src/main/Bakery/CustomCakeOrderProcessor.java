package main.Bakery;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

// Custom implementation of OrderProcessor for custom cake orders
public class CustomCakeOrderProcessor extends OrderProcessor {
    private TextArea logArea;  // TextArea for logging

    // Constructor that accepts TextArea for logging and passes it to the superclass
    public CustomCakeOrderProcessor(TextArea logArea) {
        super(logArea);  // Pass the TextArea to the OrderProcessor constructor
        this.logArea = logArea;  // Initialize the logArea for use in this class as well
    }

    @Override
    protected String[] getProcessingStages() {
        return new String[]{"Mixing", "Baking", "Decorating", "Boxing"};
    }

    // Utility method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }

    @Override
    protected void processOrder(Order order) {
        log("\n--- Processing Custom Cake Order ---");
        super.processOrder(order);
    }
}
