package observers;

public class BillingSystem implements Observer {

    @Override
    public void update(String itemName) {
        System.out.println("BillingSystem: Adding " + itemName + " to the bill.");
        // Logic to add item to bill goes here
    }
}
