package main.Adapter;

public class Tesla1ToTeslaAdapter implements UniversalCharger {
    private Tesla1Charger ogCharger;

    public Tesla1ToTeslaAdapter(Tesla1Charger ogCharger) {
        this.ogCharger = ogCharger;
    }

    @Override
    public void charge() {
        System.out.println("Using Tesla1 to Tesla adapter...");
        ogCharger.chargeWithTesla1();
    }
}

