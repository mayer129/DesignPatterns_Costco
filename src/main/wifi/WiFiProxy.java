package wifi;

public class WiFiProxy implements WiFiAccess {
    private StoreWiFi storeWiFi = new StoreWiFi();

    @Override
    public void grantAccess(String customerName) {
        // The WiFiProxy no longer handles agreement check logic.
        // The MainApp class will handle the checkbox agreement before calling this method.
        storeWiFi.grantAccess(customerName);  // Forward the request to the real Wi-Fi system
    }
}
