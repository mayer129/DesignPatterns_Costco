class OrderFoodCommand implements Command {
    private FoodItem foodItem;
    private Soda soda;

    public OrderFoodCommand(FoodItem foodItem, Soda soda) {
        this.foodItem = foodItem;
        this.soda = soda;
    }

    public void execute() {
        System.out.println("Ordered: " + foodItem.getDescription() + " - $" + foodItem.getCost());
        System.out.println("Fountain Drink: " + soda.getDescription());
    }
}