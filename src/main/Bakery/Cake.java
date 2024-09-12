package main.Bakery;

public class Cake {
    private String color;
    private String flavor;

    public void setColor(String color) {
        this.color = color;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public String getFlavor() {
        return this.flavor;
    }

    public String getColor() {
        return this.color;
    }

    @Override
    public String toString() {
        return "Cake [color=" + color + ", flavor=" + flavor + "]";
    }
}