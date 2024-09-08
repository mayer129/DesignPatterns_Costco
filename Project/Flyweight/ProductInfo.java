// ProductInfo.java
//This class contains the intrinsic data that will be shared: product description and price.
public class ProductInfo {
    private String description;
    private double price;

    public ProductInfo(String description, double price) {
        this.description = description;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Description: " + description + ", Price: $" + price;
    }
}
