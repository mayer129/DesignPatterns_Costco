package main.wifi;

import javafx.scene.control.TextArea;

public class WiFiProxy implements WiFiAccess {
    private StoreWiFi storeWiFi;
    private boolean hasAgreedToPolicy;
    private TextArea logArea;  // TextArea for logging

    // Constructor to pass the agreement status and logArea
    public WiFiProxy(boolean hasAgreedToPolicy, TextArea logArea) {
        this.hasAgreedToPolicy = hasAgreedToPolicy;
        this.logArea = logArea;
        this.storeWiFi = new StoreWiFi(logArea);  // Pass logArea to StoreWiFi constructor
    }

    @Override
    public void grantAccess(String customerName) {
        if (hasAgreedToPolicy) {
            storeWiFi.grantAccess(customerName);  // Forward the request if policy is agreed
        } else {
            logArea.appendText("Wi-Fi access denied. You must agree to the data collection policy.\n");
        }
    }
}
