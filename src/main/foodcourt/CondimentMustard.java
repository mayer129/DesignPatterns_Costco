package foodcourt;

public class CondimentMustard extends CondimentDecorator {
    public CondimentMustard(FoodItem foodItem) {
        super(foodItem);
    }

    public String getDescription() {
        return foodItem.getDescription() + ", Mustard";
    }
}
