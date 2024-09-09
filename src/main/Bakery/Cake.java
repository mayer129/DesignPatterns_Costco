public class Cake {
    private String color;
    private String flavor;

    public void setColor(String color) {
        this.color = color;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    @Override
    public String toString() {
        return "Cake [color=" + color + ", flavor=" + flavor + "]";
    }
}