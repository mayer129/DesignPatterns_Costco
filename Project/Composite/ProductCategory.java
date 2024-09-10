// ProductCategory.java
//This represents categories like Groceries and Toiletries,
// and can hold both individual products and sub-categories.
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
    public ProductComponent getChild(int index) {
        return productComponents.get(index);
    }

    @Override
    public String getName() {
        return categoryName;
    }

    @Override
    public void displayInfo() {
        System.out.println("Category: " + getName());
        for (ProductComponent productComponent : productComponents) {
            productComponent.displayInfo();
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
}
