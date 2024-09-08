// StockManager.java
//This class is the client that creates stock items and retrieves
//shared product information via the ProductFactory.
import java.util.ArrayList;
import java.util.List;

public class StockManager {
    private List<StockItem> stockItems = new ArrayList<>();
    private ProductFactory productFactory;

    public StockManager(ProductFactory productFactory) {
        this.productFactory = productFactory;
    }

    public void addStockItem(String sku, int quantity, String description, double price) {
        ProductInfo productInfo = productFactory.getProductInfo(description, price);
        StockItem stockItem = new StockItem(sku, quantity, productInfo);
        stockItems.add(stockItem);
    }

    public void displayStockItems() {
        for (StockItem item : stockItems) {
            System.out.println(item);
        }
    }
}
