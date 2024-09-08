// Strategy interface for different pricing strategies
public interface PricingStrategy {
    double calculatePrice(Cake cake); // Method to calculate price based on strategy
}

// Flat rate pricing strategy implementation
public class FlatRatePricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Cake cake) {
        return 15.00; // Flat rate for all cakes
    }
}

// Custom pricing strategy that calculates price based on flavor and color
public class CustomPricingStrategy implements PricingStrategy {
    @Override
    public double calculatePrice(Cake cake) {
        double basePrice = 10.00;
        double flavorMultiplier = cake.getFlavor().equals("chocolate") ? 1.5 : 1.2;
        return basePrice * flavorMultiplier + (cake.getColor().equals("red") ? 2.00 : 1.00);
    }
}
