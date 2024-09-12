package main.Bakery;

// Flat rate pricing strategy implementation
public class FlatRatePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Cake cake) {
        return 15.00; // Flat rate for all cakes
    }
}