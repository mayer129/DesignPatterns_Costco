// ProductFactory.java
//The factory is responsible for creating and managing flyweight objects.
// It ensures that the same ProductInfo object is reused for items with the
// same description and price.
import java.util.HashMap;
import java.util.Map;

public class ProductFactory {
    private Map<String, ProductInfo> productInfoMap = new HashMap<>();

    public ProductInfo getProductInfo(String description, double price) {
        String key = description + price;
        if (!productInfoMap.containsKey(key)) {
            productInfoMap.put(key, new ProductInfo(description, price));
            System.out.println("Created new ProductInfo for: " + description);
        }
        return productInfoMap.get(key);
    }

    public int getTotalProductsCreated() {
        return productInfoMap.size();
    }
}
