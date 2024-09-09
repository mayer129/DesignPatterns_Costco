package employees;

import java.util.ArrayList;
import java.util.List;
import observers.Observer;
import payment.PaymentProcessor;
import payment.PaymentProcessorPool;
//import subject.Subject;

public class Cashier extends Employee implements Subject {

    private List<Observer> observers; // List of observers

    // Constructor
    public Cashier(String name, String employeeID) {
        super(name, employeeID);
        this.observers = new ArrayList<>(); // Initialize observer list
    }

    public void handlePayment(PaymentProcessorPool pool, double amount, String customerDetails, String paymentDetails){
        System.out.println("Cashier clicked 'Proceed to Payment'");

        // Borrow a payment processor from the pool
        PaymentProcessor processor = pool.borrowProcessor();
        
        // Process the payment
        boolean paymentSuccess = processor.processPayment(amount, customerDetails, paymentDetails);

        if (paymentSuccess) {
            System.out.println("Payment was successful!");
        } else {
            System.out.println("Payment failed. Please try again.");
        }

        // Return the processor back to the pool
        pool.returnProcessor(processor);
    }



    // Existing login method (unchanged)
    @Override
    public void login() {
        if (accessGranted) {
            System.out.println("Cashier " + name + " logged in with ID: " + employeeID);
        } else {
            System.out.println("Access denied for Cashier " + name);
        }
    }

    // Existing logout method (unchanged)
    @Override
    public void logout() {
        System.out.println("Cashier " + name + " logged out.");
    }

    // New Observer Pattern Methods
    // ===========================
    
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
        System.out.println("Scanning item: " + itemName);
        notifyObservers(itemName); // Notify all observers of the scanned item
    }
}
