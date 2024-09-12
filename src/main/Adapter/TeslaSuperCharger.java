package main.Adapter;

import javafx.scene.control.TextArea;

public class TeslaSuperCharger {
    private TextArea logArea;

    // Constructor to pass TextArea for logging
    public TeslaSuperCharger(TextArea logArea) {
        this.logArea = logArea;
    }

    public void chargeWithTeslaSuperCharger() {
        logArea.appendText("Charging with Tesla SuperCharger.\n");
    }
}
