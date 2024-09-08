// ProductComponent.java
//The base interface defines the common methods for both leaf nodes
//(individual items) and composite nodes (categories).
public abstract class ProductComponent {
    public void add(ProductComponent productComponent) {
        throw new UnsupportedOperationException();
    }

    public void remove(ProductComponent productComponent) {
        throw new UnsupportedOperationException();
    }

    public ProductComponent getChild(int index) {
        throw new UnsupportedOperationException();
    }

    public String getName() {
        throw new UnsupportedOperationException();
    }

    public double getPrice() {
        throw new UnsupportedOperationException();
    }

    public void displayInfo() {
        throw new UnsupportedOperationException();
    }

    public double getTotalPrice() {
        throw new UnsupportedOperationException();
    }
}
