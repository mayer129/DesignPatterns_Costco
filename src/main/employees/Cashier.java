package employees;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;
import observers.Observer;
import payment.PaymentProcessor;
import payment.PaymentProcessorPool;

public class Cashier extends Employee implements Subject {

    private List<Observer> observers;  // List of observers
    private TextArea logArea;          // TextArea for logging

    // Constructor
    public Cashier(String name, String employeeID, TextArea logArea) {
        super(name, employeeID);
        this.observers = new ArrayList<>();  // Initialize observer list
        this.logArea = logArea;              // Initialize the logArea for logging
    }

    // Handle payment with the payment processor pool
    public void handlePayment(PaymentProcessorPool pool, double amount, String customerDetails, String paymentDetails) {
        log("Cashier clicked 'Proceed to Payment'");

        // Borrow a payment processor from the pool
        PaymentProcessor processor = pool.borrowProcessor();

        // Process the payment
        boolean paymentSuccess = processor.processPayment(amount, customerDetails, paymentDetails);

        if (paymentSuccess) {
            log("Payment was successful!");
        } else {
            log("Payment failed. Please try again.");
        }

        // Return the processor back to the pool
        pool.returnProcessor(processor);
    }

    // Existing login method (now logs to TextArea)
    @Override
    public void login() {
        if (accessGranted) {
            log("Cashier " + name + " logged in with ID: " + employeeID);
        } else {
            log("Access denied for Cashier " + name);
        }
    }

    // Existing logout method (now logs to TextArea)
    @Override
    public void logout() {
        log("Cashier " + name + " logged out.");
    }

    // Register an observer
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    // Remove an observer
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Notify all observers when an item is scanned
    @Override
    public void notifyObservers(String itemName) {
        for (Observer observer : observers) {
            observer.update(itemName);
        }
    }

    // New method: Scan an item and notify all observers
    public void scanItem(String itemName) {
        log("Scanning item: " + itemName);
        notifyObservers(itemName);  // Notify all observers of the scanned item
    }

    // Utility method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
