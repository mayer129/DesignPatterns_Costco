package wifi;

public class StoreWiFi implements WiFiAccess {
    @Override
    public void grantAccess(String customerName) {
        System.out.println("Wi-Fi access granted to " + customerName + ". Enjoy your free Wi-Fi!!");
    }
}
