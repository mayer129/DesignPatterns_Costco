package main.Adapter;

import javafx.scene.control.TextArea;

public class Tesla1ToTeslaAdapter implements UniversalCharger {
    private Tesla1Charger ogCharger;
    private TextArea logArea;

    // Constructor to pass the charger and TextArea for logging
    public Tesla1ToTeslaAdapter(Tesla1Charger ogCharger, TextArea logArea) {
        this.ogCharger = ogCharger;
        this.logArea = logArea;
    }

    @Override
    public void charge() {
        logArea.appendText("Using Tesla1 to Tesla adapter...\n");
        ogCharger.chargeWithTesla1();
    }
}
