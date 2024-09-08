public abstract class CondimentDecorator implements FoodItem {
    protected FoodItem foodItem;

    public CondimentDecorator(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public abstract String getDescription();
    public double getCost() { return foodItem.getCost(); } // Condiments are free
}