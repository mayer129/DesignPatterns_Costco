package composite;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory extends ProductComponent {
    private List<ProductComponent> productComponents = new ArrayList<>();
    private String categoryName;

    public ProductCategory(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public void add(ProductComponent productComponent) {
        productComponents.add(productComponent);
    }

    @Override
    public void remove(ProductComponent productComponent) {
        productComponents.remove(productComponent);
    }

    @Override
    public String getName() {
        return categoryName;
    }

    @Override
    public void displayInfo(TextArea logArea) {
        log("Category: " + getName(), logArea);
        for (ProductComponent productComponent : productComponents) {
            productComponent.displayInfo(logArea);
        }
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = 0.0;
        for (ProductComponent productComponent : productComponents) {
            totalPrice += productComponent.getTotalPrice();
        }
        return totalPrice;
    }

    // Log messages to TextArea
    private void log(String message, TextArea logArea) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
