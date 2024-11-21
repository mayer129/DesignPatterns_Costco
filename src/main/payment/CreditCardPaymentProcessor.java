package payment;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class CreditCardPaymentProcessor implements PaymentProcessor {

    private TextArea logArea;  // Reference to the TextArea for logging

    // Constructor to accept the TextArea
    public CreditCardPaymentProcessor(TextArea logArea) {
        this.logArea = logArea;
    }

    @Override
    public boolean processPayment(double amount, String customerDetails, String paymentDetails) {
        // Log the start of payment processing
        log("Processing payment of $" + amount + " for " + customerDetails);

        try {
            // Simulate network latency or processing delay
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Log the success of payment processing
        log("Payment of $" + amount + " for " + customerDetails + " completed successfully.");
        return true;  // Simulate successful payment
    }

    // Method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
