// ReportVisitor.java
//This visitor will generate a report of all stock items.
import java.util.ArrayList;
import java.util.List;

public class ReportVisitor implements Visitor {
    private List<String> report = new ArrayList<>();

    @Override
    public void visit(StockItem stockItem) {
        String reportEntry = "SKU: " + stockItem.getSku() +
                ", Description: " + stockItem.getProductInfo().getDescription() +
                ", Price: $" + stockItem.getProductInfo().getPrice() +
                ", Quantity: " + stockItem.getQuantity();
        report.add(reportEntry);
    }

    public void printReport() {
        System.out.println("Stock Report:");
        for (String entry : report) {
            System.out.println(entry);
        }
    }
}
