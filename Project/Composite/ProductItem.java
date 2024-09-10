// ProductItem.java
//This represents individual products like groceries (e.g., apples, milk)
//and toiletries (e.g., shampoo, soap). Each product has a name and price.
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
    public void displayInfo() {
        System.out.println("Product: " + getName() + ", Price: $" + getPrice());
    }

    @Override
    public double getTotalPrice() {
        return getPrice();
    }
}
