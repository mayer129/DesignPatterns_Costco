import java.util.LinkedList;
import java.util.Queue;

public class OrderProcessor {
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
}