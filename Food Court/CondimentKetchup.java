public class CondimentKetchup extends CondimentDecorator {
    public CondimentKetchup(FoodItem foodItem) {
        super(foodItem);
    }

    public String getDescription() {
        return foodItem.getDescription() + ", Ketchup";
    }
}