// DiscountVisitor.java
//This visitor will apply a bulk discount to stock items based on their quantity.
public class DiscountVisitor implements Visitor {
    private double discountThreshold;
    private double discountRate;

    public DiscountVisitor(double discountThreshold, double discountRate) {
        this.discountThreshold = discountThreshold;
        this.discountRate = discountRate;
    }

    @Override
    public void visit(StockItem stockItem) {
        if (stockItem.getQuantity() >= discountThreshold) {
            double oldPrice = stockItem.getProductInfo().getPrice();
            double newPrice = oldPrice - (oldPrice * discountRate);
            System.out.println("Applying bulk discount to SKU: " + stockItem.getSku() +
                    ". Old Price: $" + oldPrice + ", New Price: $" + newPrice);
        }
    }
}
