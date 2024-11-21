package main.Flyweight;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

import java.util.ArrayList;
import java.util.List;

public class ReportVisitor implements Visitor {
    private List<String> report = new ArrayList<>();
    private TextArea logArea;

    // Constructor to pass TextArea for logging
    public ReportVisitor(TextArea logArea) {
        this.logArea = logArea;
    }

    @Override
    public void visit(StockItem stockItem) {
        String reportEntry = "SKU: " + stockItem.getSku() +
                ", Description: " + stockItem.getProductInfo().getDescription() +
                ", Price: $" + stockItem.getProductInfo().getPrice() +
                ", Quantity: " + stockItem.getQuantity();
        report.add(reportEntry);
    }

    public void printReport() {
        log("Stock Report:");
        for (String entry : report) {
            log(entry);
        }
    }

    // Logging method
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
