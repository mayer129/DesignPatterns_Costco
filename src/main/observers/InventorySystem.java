package observers;

public class InventorySystem implements Observer {

    @Override
    public void update(String itemName) {
        System.out.println("InventorySystem: Reducing stock for " + itemName);
        // Logic to reduce inventory stock goes here
    }
}
