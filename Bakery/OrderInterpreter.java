import java.util.ArrayList;
import java.util.List;

public class OrderInterpreter {
    public List<Cake> interpret(String input) {
        List<Cake> cakes = new ArrayList<>();
        String[] orders = input.split(",");

        for (String order : orders) {
            String[] parts = order.trim().split(" ");
            if (parts.length == 4 && (parts[3].equals("cake") || parts[3].equals("cakes"))) {
                int quantity = Integer.parseInt(parts[0]);
                String color = parts[1];
                String flavor = parts[2];

                for (int i = 0; i < quantity; i++) {
                    CakeBuilder builder = new CustomCakeBuilder();
                    builder.setColor(color);
                    builder.setFlavor(flavor);
                    cakes.add(builder.build());
                }
            }
        }

        return cakes;
    }
}