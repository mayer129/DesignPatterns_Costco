// VisitableStockManager.java
//This class manages the collection of stock items and allows visitors to visit them.
import java.util.ArrayList;
import java.util.List;

public class VisitableStockManager {
    private List<StockItem> stockItems = new ArrayList<>();

    public void addStockItem(StockItem stockItem) {
        stockItems.add(stockItem);
    }

    public void accept(Visitor visitor) {
        for (StockItem stockItem : stockItems) {
            stockItem.accept(visitor);
        }
    }
}
