package payment;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.concurrent.ConcurrentLinkedQueue;

public class PaymentProcessorPool {
    private ConcurrentLinkedQueue<PaymentProcessor> availableProcessors;
    private int poolSize;
    private static PaymentProcessorPool instance;
    private TextArea logArea;  // Add a reference to the TextArea for logging

    // Private constructor for singleton pattern
    private PaymentProcessorPool(int poolSize, TextArea logArea) {
        this.poolSize = poolSize;
        this.logArea = logArea;
        availableProcessors = new ConcurrentLinkedQueue<>();

        // Initialize the pool with payment processors
        for (int i = 0; i < poolSize; i++) {
            availableProcessors.add(new payment.CreditCardPaymentProcessor(logArea));
            log("Created initial PaymentProcessor #" + (i + 1));
        }
    }

    // Get the singleton instance
    public static synchronized PaymentProcessorPool getInstance(int poolSize, TextArea logArea) {
        if (instance == null) {
            instance = new PaymentProcessorPool(poolSize, logArea);
        }
        return instance;
    }

    // Borrow a processor from the pool
    public PaymentProcessor borrowProcessor() {
        PaymentProcessor processor = availableProcessors.poll();
        if (processor == null) {
            // Pool is exhausted, create a new processor
            log("Pool exhausted! Creating a new PaymentProcessor.");
            processor = new payment.CreditCardPaymentProcessor(logArea);
        } else {
            log("Borrowing a PaymentProcessor from the pool.");
        }
        return processor;
    }

    // Return a processor back to the pool
    public void returnProcessor(PaymentProcessor processor) {
        log("Returning a PaymentProcessor back to the pool.\n\n");
        availableProcessors.offer(processor);
    }

    // Check how many processors are currently in the pool
    public int availableProcessors() {
        return availableProcessors.size();
    }

    // Method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
