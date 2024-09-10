package composite;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class ProductItem extends ProductComponent {
    private String name;
    private double price;

    public ProductItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void displayInfo(TextArea logArea) {
        log("Product: " + name + " - Price: $" + price, logArea);
    }

    @Override
    public double getTotalPrice() {
        return price;
    }

    // Log messages to TextArea
    private void log(String message, TextArea logArea) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
