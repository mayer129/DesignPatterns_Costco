package main.Adapter;

import javafx.scene.control.TextArea;

public class Tesla1Charger {
    private TextArea logArea;

    // Constructor to pass TextArea for logging
    public Tesla1Charger(TextArea logArea) {
        this.logArea = logArea;
    }

    public void chargeWithTesla1() {
        logArea.appendText("Charging with Tesla1.0 charger.\n");
    }
}
