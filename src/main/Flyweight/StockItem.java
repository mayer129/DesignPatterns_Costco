// StockItem.java
package main.Flyweight;
//This class contains extrinsic data (SKU, quantity) that is unique to each stock item.
// The product information (description, price) is shared via the ProductInfo flyweight.
public class StockItem {
    private String sku;
    private int quantity;
    private ProductInfo productInfo;  // Flyweight reference

    public StockItem(String sku, int quantity, ProductInfo productInfo) {
        this.sku = sku;
        this.quantity = quantity;
        this.productInfo = productInfo;
    }

    public String getSku() {
        return sku;
    }

    public int getQuantity() {
        return quantity;
    }

    public ProductInfo getProductInfo() {
        return productInfo;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "SKU: " + sku + ", Quantity: " + quantity + ", " + productInfo;
    }
}

