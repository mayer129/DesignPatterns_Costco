// The Mediator class facilitates communication between the OrderProcessor
// and OrderInterpreter
public class BakeryMediator {
    private OrderProcessor orderProcessor;
    private OrderInterpreter orderInterpreter;

    // Constructor to initialize the mediator with processor and interpreter
    public BakeryMediator(OrderProcessor processor, OrderInterpreter interpreter) {
        this.orderProcessor = processor;
        this.orderInterpreter = interpreter;
    }

    // Method to place an order by interpreting the input and adding it to
    // the processor
    public void placeOrder(String orderInput) {
        var cakes = orderInterpreter.interpret(orderInput); // Interpret the order input
        if (cakes.isEmpty()) {
            System.out.println("Invalid order. Please try again.");
            return;
        }

        // Add each interpreted cake as an order in the processor
        for (var cake : cakes) {
            Order order = new Order(cake);
            orderProcessor.addOrder(order);
            System.out.println("Order placed: " + order + " - Price: $15.00");
        }
    }

    // Method to trigger the processing of orders in the queue
    public void processOrders() {
        orderProcessor.processOrders();
    }
}
