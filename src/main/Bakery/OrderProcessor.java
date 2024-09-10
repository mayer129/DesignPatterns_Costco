package bakery;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.LinkedList;
import java.util.Queue;

public class OrderProcessor {
    /*private final Queue<Order> orderQueue = new LinkedList<>();
    private final OrderCaretaker caretaker = new OrderCaretaker();
    private TextArea logArea;  // Add TextArea for logging

    // Constructor to accept TextArea for logging
    public OrderProcessor(TextArea logArea) {
        this.logArea = logArea;
    }

    // Method to add an order
    public void addOrder(Order order) {
        orderQueue.offer(order);
        caretaker.addMemento(order.getOrderNumber(), order.saveState());
        log("Order added to queue: " + order);
    }

    // Method to process all orders
    public void processOrders() {
        log("\n--- Processing Orders ---");  // Add space for better readability
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            processOrder(order);
        }
        log("--- All Orders Processed ---");
    }

    // Method to process a single order
    private void processOrder(Order order) {
        String[] stages = {"Mixing", "Baking", "Decorating", "Boxing"};
        for (String stage : stages) {
            log("Starting " + stage + " for " + order);
            updateOrderStatus(order, stage + " In Progress");
            simulateProcessing();

            if (Math.random() < 0.1) { // 10% chance for issue
                handleIssue(order, stage);
                if (order.getStatus().equals("Queued")) {
                    // If the order was reset to Queued, start over from the beginning
                    processOrder(order);
                    return;
                }
            } else {
                log("Completed " + stage + " for " + order);
            }
        }
        updateOrderStatus(order, "Ready");
        log("Order completed: " + order);
    }

    // Method to update the order status and save the state
    private void updateOrderStatus(Order order, String status) {
        caretaker.addMemento(order.getOrderNumber(), order.saveState());
        order.setStatus(status);
        log("Updated " + order);
    }

    // Simulate processing with a delay
    private void simulateProcessing() {
        try {
            Thread.sleep(1000); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // Handle issues during the order processing
    private void handleIssue(Order order, String stage) {
        log("Issue found with " + order + " during " + stage + " ~ Let me make you a new one!");
        OrderMemento previousState = caretaker.getMemento(order.getOrderNumber());
        if (previousState != null) {
            order.restoreState(previousState);
            log("Order reverted to previous state: " + order);
        } else {
            log("Unable to revert order state. Restarting from the beginning.");
            order.setStatus("Queued");
        }
    }*/

    import java.util.LinkedList;
import java.util.Queue;

    // Abstract class representing the OrderProcessor that uses the Template Method pattern
    public abstract class OrderProcessor {
        private final Queue<Order> orderQueue = new LinkedList<>(); // Queue to hold orders
        private final OrderCaretaker caretaker = new OrderCaretaker(); // Memento to save order states

        // Adds an order to the queue and saves its initial state
        public void addOrder(Order order) {
            orderQueue.offer(order); // Add order to the queue
            caretaker.addMemento(order.getOrderNumber(), order.saveState()); // Save the initial state
            System.out.println("Order added to queue: " + order);
        }

        // Processes all the orders in the queue
        public void processOrders() {
            while (!orderQueue.isEmpty()) {
                Order order = orderQueue.poll(); // Poll orders one by one
                processOrder(order); // Process each order
            }
        }

        // Template method that defines the sequence of steps for processing an order
        protected void processOrder(Order order) {
            String[] stages = getProcessingStages(); // Get the specific stages for the order (defined in subclasses)
            for (String stage : stages) {
                System.out.println("Starting " + stage + " for " + order);
                updateOrderStatus(order, stage + " In Progress");
                simulateProcessing(); // Simulate the time it takes to process each stage

                if (Math.random() < 0.1) { // 10% chance for an issue to occur
                    handleIssue(order, stage); // Handle any issue that occurs
                    if (order.getStatus().equals("Queued")) {
                        processOrder(order); // If reset to queued, restart processing
                        return;
                    }
                } else {
                    System.out.println("Completed " + stage + " for " + order);
                }
            }
            updateOrderStatus(order, "Ready"); // Mark the order as ready
            System.out.println("Order completed: " + order);
        }

        // Abstract method to define processing stages for different types of orders
        protected abstract String[] getProcessingStages();

        // Updates the status of the order and saves the current state
        private void updateOrderStatus(Order order, String status) {
            caretaker.addMemento(order.getOrderNumber(), order.saveState()); // Save the state
            order.setStatus(status); // Update status
            System.out.println("Updated " + order);
        }

        // Simulates time taken for each stage
        private void simulateProcessing() {
            try {
                Thread.sleep(1000); // Simulate a 1-second delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Handles issues encountered during processing by restoring the previous state
        private void handleIssue(Order order, String stage) {
            System.out.println("Issue encountered in " + stage + " for " + order);
            order.restoreState(caretaker.getMemento(order.getOrderNumber())); // Restore to previous state
            updateOrderStatus(order, "Queued"); // Reset to queued status
        }
    }
    // Utility method to log messages to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
