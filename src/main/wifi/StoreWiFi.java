package main.wifi;

import javafx.scene.control.TextArea;

public class StoreWiFi implements WiFiAccess {
    private TextArea logArea;

    public StoreWiFi(TextArea logArea) {
        this.logArea = logArea;
    }

    @Override
    public void grantAccess(String customerName) {
        logArea.appendText("Wi-Fi access granted to " + customerName + ". Enjoy your free Wi-Fi!!\n");
    }
}
