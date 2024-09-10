package app;
import javafx.scene.control.TextArea;

public abstract class SupportHandler {
    protected SupportHandler nextHandler;

    // Method to set the next handler in the chain
    public void setNextHandler(SupportHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    // Updated method to accept TextArea for logging
    public abstract void handleRequest(SupportRequest request, TextArea logArea);
}
