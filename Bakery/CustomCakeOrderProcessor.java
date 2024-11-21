// Concrete implementation of the OrderProcessor for custom cakes using the
//Template Method pattern
public class CustomCakeOrderProcessor extends OrderProcessor {
    // Defines specific stages for custom cake processing
    @Override
    protected String[] getProcessingStages() {
        return new String[] {"Mixing", "Baking", "Decorating", "Customizing", "Boxing"};
    }
}
