package main.Bakery;

import javafx.scene.control.TextArea;

// The Mediator class facilitates communication between the OrderProcessor
// and OrderInterpreter
public class BakeryMediator {
    private OrderProcessor orderProcessor;
    private OrderInterpreter orderInterpreter;
    private TextArea logArea;  // TextArea for logging

    // Constructor to initialize the mediator with processor, interpreter, and TextArea for logging
    public BakeryMediator(OrderProcessor processor, OrderInterpreter interpreter, TextArea logArea) {
        this.orderProcessor = processor;
        this.orderInterpreter = interpreter;
        this.logArea = logArea;  // Set the log area
    }

    // Method to place an order by interpreting the input and adding it to the processor
    public void placeOrder(String orderInput) {
        var cakes = orderInterpreter.interpret(orderInput); // Interpret the order input
        if (cakes.isEmpty()) {
            log("Invalid order. Please try again.");
            return;
        }

        // Add each interpreted cake as an order in the processor
        for (var cake : cakes) {
            Order order = new Order(cake);
            orderProcessor.addOrder(order);
            log("Order placed: " + order + " - Price: $15.00");
        }
    }

    // Method to trigger the processing of orders in the queue
    public void processOrders() {
        orderProcessor.processOrders();
    }

    // Utility method to log messages to the TextArea
    private void log(String message) {
        logArea.appendText(message + "\n");  // Append message to the TextArea
    }
}
