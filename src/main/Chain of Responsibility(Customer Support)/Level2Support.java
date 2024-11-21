package app;

public class Level2Support extends SupportHandler {

    @Override
    public void handleRequest(SupportRequest request, javafx.scene.control.TextArea logArea) {
        if (request.getIssueType().equalsIgnoreCase("intermediate")) {
            logArea.appendText("Level 2 Support: Handling intermediate request -> " + request.getDescription() + "\n");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request, logArea);
            } else {
                logArea.appendText("No handler found for the request: " + request.getDescription() + "\n");
            }
        }
    }
}
