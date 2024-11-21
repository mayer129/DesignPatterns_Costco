import java.util.LinkedList;
import java.util.Queue;

/*public class OrderProcessor {
    private final Queue<Order> orderQueue = new LinkedList<>();
    private final OrderCaretaker caretaker = new OrderCaretaker();

    public void addOrder(Order order) {
        orderQueue.offer(order);
        caretaker.addMemento(order.getOrderNumber(), order.saveState());
        System.out.println("Order added to queue: " + order);
    }

    public void processOrders() {
        while (!orderQueue.isEmpty()) {
            Order order = orderQueue.poll();
            processOrder(order);
        }
    }

    private void processOrder(Order order) {
        String[] stages = {"Mixing", "Baking", "Decorating", "Boxing"};
        for (String stage : stages) {
            System.out.println("Starting " + stage + " for " + order);
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
                System.out.println("Completed " + stage + " for " + order);
            }
        }
        updateOrderStatus(order, "Ready");
        System.out.println("Order completed: " + order);
    }

    private void updateOrderStatus(Order order, String status) {
        caretaker.addMemento(order.getOrderNumber(), order.saveState());
        order.setStatus(status);
        System.out.println("Updated " + order);
    }

    private void simulateProcessing() {
        try {
            Thread.sleep(1000); // Simulate processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void handleIssue(Order order, String stage) {
        System.out.println("Issue found with " + order + " ~ Let me make you a new one!");
        OrderMemento previousState = caretaker.getMemento(order.getOrderNumber());
        if (previousState != null) {
            order.restoreState(previousState);
            System.out.println("Order reverted to previous state: " + order);
        } else {
            System.out.println("Unable to revert order state. Restarting from the beginning.");
            order.setStatus("Queued");
        }
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
