package main.Bakery;

// Custom pricing strategy that calculates price based on flavor and color
public class CustomPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Cake cake) {
        double basePrice = 10.00;
        double flavorMultiplier = cake.getFlavor().equals("chocolate") ? 1.5 : 1.2;
        return basePrice * flavorMultiplier + (cake.getColor().equals("red") ? 2.00 : 1.00);
    }
}
