package main.Flyweight;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.HashMap;
import java.util.Map;

public class ProductFactory {
    private Map<String, ProductInfo> productInfoMap = new HashMap<>();
    private TextArea logArea;

    // Constructor accepts TextArea for logging
    public ProductFactory(TextArea logArea) {
        this.logArea = logArea;
    }

    public ProductInfo getProductInfo(String description, double price) {
        String key = description + price;
        if (!productInfoMap.containsKey(key)) {
            productInfoMap.put(key, new ProductInfo(description, price));
            log("Created new ProductInfo for: " + description);
        }
        return productInfoMap.get(key);
    }

    public int getTotalProductsCreated() {
        return productInfoMap.size();
    }

    // Logging method
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
