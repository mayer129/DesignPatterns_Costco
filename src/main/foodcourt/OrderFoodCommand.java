package foodcourt;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class OrderFoodCommand implements Command {
    private FoodItem foodItem;
    private Soda soda;
    private TextArea logArea;  // TextArea to log actions

    public OrderFoodCommand(FoodItem foodItem, Soda soda, TextArea logArea) {
        this.foodItem = foodItem;
        this.soda = soda;
        this.logArea = logArea;
    }

    @Override
    public void execute() {
        // Log the order details to the TextArea
        log("Ordered: " + foodItem.getDescription() + " - $" + foodItem.getCost());
        log("Fountain Drink: " + soda.getDescription());
    }

    // Method to log actions to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
