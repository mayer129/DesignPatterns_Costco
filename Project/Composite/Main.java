// CompositeDemo.java
//This class demonstrates how to use the composite structure for both Groceries and
// Toiletries categories. We will add individual items and sub-categories,
// display the information, and calculate the total price.
public class Main {
    public static void main(String[] args) {
        // Create individual product items for groceries
        ProductComponent apples = new ProductItem("Apples", 2.99);
        ProductComponent milk = new ProductItem("Milk", 1.49);
        ProductComponent bread = new ProductItem("Bread", 2.49);

        // Create a groceries category and add products to it
        ProductComponent groceriesCategory = new ProductCategory("Groceries");
        groceriesCategory.add(apples);
        groceriesCategory.add(milk);
        groceriesCategory.add(bread);

        // Create individual product items for toiletries
        ProductComponent shampoo = new ProductItem("Shampoo", 5.99);
        ProductComponent soap = new ProductItem("Soap", 3.99);

        // Create a toiletries category and add products to it
        ProductComponent toiletriesCategory = new ProductCategory("Toiletries");
        toiletriesCategory.add(shampoo);
        toiletriesCategory.add(soap);

        // Create a main Costco category to hold both groceries and toiletries
        ProductComponent costcoCategory = new ProductCategory("Costco");
        costcoCategory.add(groceriesCategory);
        costcoCategory.add(toiletriesCategory);

        // Display all categories and products
        System.out.println("Displaying the Costco product categories:");
        costcoCategory.displayInfo();

        // Calculate and display the total price of all products
        System.out.println("\nTotal price of all products: $" + costcoCategory.getTotalPrice());
    }
}
