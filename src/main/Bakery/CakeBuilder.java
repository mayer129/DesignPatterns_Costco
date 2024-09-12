package main.Bakery;

public interface CakeBuilder {
    void setColor(String color);
    void setFlavor(String flavor);
    Cake build();
}