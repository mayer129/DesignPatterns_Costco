import java.util.List;
import java.util.Scanner;

public class BakerySimulator {
    private static final OrderProcessor processor = new OrderProcessor();
    private static final OrderInterpreter interpreter = new OrderInterpreter();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to the Costco Bakery!");

        while (true) {
            System.out.println("\nWhat would you like to do?");
            System.out.println("1. Place an order");
            System.out.println("2. Process orders");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    placeOrder();
                    break;
                case 2:
                    processor.processOrders();
                    break;
                case 3:
                    System.out.println("Thank you for visiting the Costco Bakery. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void placeOrder() {
        System.out.println("Enter your cake order (e.g., '1 red chocolate cake, 2 white vanilla cakes'):");
        String orderInput = scanner.nextLine();

        List<Cake> cakes = interpreter.interpret(orderInput);

        if (cakes.isEmpty()) {
            System.out.println("Invalid order. Please try again.");
            return;
        }

        for (Cake cake : cakes) {
            Order order = new Order(cake);
            processor.addOrder(order);
            System.out.println("Order placed: " + order + " - Price: $15.00");
        }
    }
}