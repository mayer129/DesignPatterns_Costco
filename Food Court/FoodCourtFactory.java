public class FoodCourtFactory {
    public FoodItem createFoodItem(String type) {
        switch (type.toLowerCase()) {
            case "hotdog": return new HotDog();
            case "cheese pizza": return new CheesePizza();
            case "pepperoni pizza": return new PepperoniPizza();
            default: return null;
        }
    }

    public Soda createSoda(String flavor) {
        return new Soda(flavor);
    }
}