// FlyweightDemo.java
//This class demonstrates how the flyweight pattern works by creating
//multiple stock items that share the same product description and price.
public class Main {
    public static void main(String[] args) {
        // Create a product factory (Flyweight Factory)
        ProductFactory productFactory = new ProductFactory();

        // Create a visitable stock manager
        VisitableStockManager stockManager = new VisitableStockManager();

        // Add stock items to the manager using Flyweight pattern
        stockManager.addStockItem(new StockItem("SKU123", 50, productFactory.getProductInfo("Apple iPhone 13", 999.99)));
        stockManager.addStockItem(new StockItem("SKU124", 20, productFactory.getProductInfo("Apple iPhone 13", 999.99)));  // Reuses ProductInfo
        stockManager.addStockItem(new StockItem("SKU125", 150, productFactory.getProductInfo("Samsung Galaxy S21", 799.99)));
        stockManager.addStockItem(new StockItem("SKU126", 5, productFactory.getProductInfo("Apple Watch", 399.99)));

        // Flyweight Pattern Demonstration
        // Check how many unique ProductInfo objects were created
        System.out.println("\nTotal unique ProductInfo objects created (Flyweight pattern): " + productFactory.getTotalProductsCreated());

        // Apply Visitor Pattern - Generate a stock report
        ReportVisitor reportVisitor = new ReportVisitor();
        stockManager.accept(reportVisitor);
        reportVisitor.printReport();  // Prints report based on shared ProductInfo

        // Apply Visitor Pattern - Apply bulk discount
        DiscountVisitor discountVisitor = new DiscountVisitor(100, 0.10);  // 10% discount for bulk items
        stockManager.accept(discountVisitor);
    }
}