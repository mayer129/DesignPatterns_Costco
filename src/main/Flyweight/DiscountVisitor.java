package main.Flyweight;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class DiscountVisitor implements Visitor {
    private double discountThreshold;
    private double discountRate;
    private TextArea logArea;

    public DiscountVisitor(double discountThreshold, double discountRate, TextArea logArea) {
        this.discountThreshold = discountThreshold;
        this.discountRate = discountRate;
        this.logArea = logArea;
    }

    @Override
    public void visit(StockItem stockItem) {
        if (stockItem.getQuantity() >= discountThreshold) {
            double oldPrice = stockItem.getProductInfo().getPrice();
            double newPrice = oldPrice - (oldPrice * discountRate);
            log("Applying bulk discount to SKU: " + stockItem.getSku() +
                    ". Old Price: $" + oldPrice + ", New Price: $" + newPrice);
        }
    }

    // Logging method
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
