package foodcourt;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class FillSodaCommand implements Command {
    private Soda soda;
    private TextArea logArea;  // TextArea to log actions

    public FillSodaCommand(Soda soda, TextArea logArea) {
        this.soda = soda;
        this.logArea = logArea;
    }

    @Override
    public void execute() {
        // Log the action to the TextArea
        log("Filled cup with: " + soda.getDescription());
    }

    // Method to log actions to the TextArea
    private void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
