package main.Adapter;

public class Test {
    public static void main(String[] args) {
        // Using Tesla SuperCharger directly
        TeslaSuperCharger teslaCharger = new TeslaSuperCharger();
        teslaCharger.chargeWithTeslaSuperCharger();

        // Using ChademoCharger with adapter to charge a Tesla
        Tesla1Charger oldCharger = new Tesla1Charger();
        UniversalCharger adapter = new Tesla1ToTeslaAdapter(oldCharger);
        adapter.charge();
    }
}

