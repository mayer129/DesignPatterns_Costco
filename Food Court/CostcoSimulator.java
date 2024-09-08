import java.util.Scanner;

public class CostcoSimulator {
    private static Scanner scanner = new Scanner(System.in);
    private static FoodCourtFactory factory = new FoodCourtFactory();

    public static void main(String[] args) {
        System.out.println("Welcome to Costco!");
        while (true) {
            System.out.println("\nWhere would you like to go?");
            System.out.println("1. Food Court");
            System.out.println("2. Exit Costco");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                visitFoodCourt();
            } else if (choice.equals("2")) {
                System.out.println("Thank you for visiting Costco. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void visitFoodCourt() {
        System.out.println("\nWelcome to the Food Court!");

        // Order food
        FoodItem order = null;
        while (order == null) {
            System.out.println("What would you like to order? (hotdog/cheese pizza/pepperoni pizza)");
            String foodChoice = scanner.nextLine();
            order = factory.createFoodItem(foodChoice);
            if (order == null) {
                System.out.println("Sorry, we don't have that item. Please choose again.");
            }
        }

        // Add condiments
        order = addCondiments(order);

        // Order soda
        Soda soda = null;
        while (soda == null) {
            System.out.println("\nWhat fountain drink would you like? (coca-cola/sprite/fanta)");
            String sodaChoice = scanner.nextLine();
            if (sodaChoice.equalsIgnoreCase("coca-cola") ||
                    sodaChoice.equalsIgnoreCase("sprite") ||
                    sodaChoice.equalsIgnoreCase("fanta")) {
                soda = factory.createSoda(sodaChoice);
            } else {
                System.out.println("Sorry, we don't have that soda. Please choose again.");
            }
        }

        // Execute food order command
        new OrderFoodCommand(order, soda).execute();
    }

    private static FoodItem addCondiments(FoodItem item) {
        while (true) {
            System.out.println("\nWould you like to add any condiments? (ketchup/mustard/relish/done)");
            String condiment = scanner.nextLine().toLowerCase();

            if (condiment.equals("done")) {
                break;
            }

            switch (condiment) {
                case "ketchup":
                    item = new CondimentKetchup(item);
                    break;
                case "mustard":
                    item = new CondimentMustard(item);
                    break;
                case "relish":
                    item = new CondimentRelish(item);
                    break;
                default:
                    System.out.println("Sorry, we don't have that condiment. Please choose again or type 'done' to finish.");
            }
        }

        return item;
    }
}