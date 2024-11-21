package foodcourt;

public class CondimentRelish extends CondimentDecorator {
    public CondimentRelish(FoodItem foodItem) {
        super(foodItem);
    }

    public String getDescription() {
        return foodItem.getDescription() + ", Relish";
    }
}