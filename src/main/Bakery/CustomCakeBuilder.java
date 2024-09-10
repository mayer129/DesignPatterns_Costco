package bakery;

public class CustomCakeBuilder implements CakeBuilder {
    private Cake cake;

    public CustomCakeBuilder() {
        this.cake = new Cake();
    }

    @Override
    public void setColor(String color) {
        cake.setColor(color);
    }

    @Override
    public void setFlavor(String flavor) {
        cake.setFlavor(flavor);
    }

    @Override
    public Cake build() {
        return cake;
    }
}