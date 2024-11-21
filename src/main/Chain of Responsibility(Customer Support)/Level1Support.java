package app;
public class Level1Support extends SupportHandler {

    @Override
    public void handleRequest(SupportRequest request, javafx.scene.control.TextArea logArea) {
        if (request.getIssueType().equalsIgnoreCase("basic")) {
            logArea.appendText("Level 1 Support: Handling basic request -> " + request.getDescription() + "\n");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request, logArea);
            } else {
                logArea.appendText("No handler found for the request: " + request.getDescription() + "\n");
            }
        }
    }
}
