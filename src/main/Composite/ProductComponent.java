package composite;

import javafx.scene.control.TextArea;

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

    public void displayInfo(TextArea logArea) {
        throw new UnsupportedOperationException();
    }

    public double getTotalPrice() {
        throw new UnsupportedOperationException();
    }
}
