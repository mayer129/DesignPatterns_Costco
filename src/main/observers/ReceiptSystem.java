package observers;

public class ReceiptSystem implements Observer {

    @Override
    public void update(String itemName) {
        System.out.println("ReceiptSystem: Logging " + itemName + " for the receipt.");
        // Logic to log item for receipt generation goes here
    }
}
