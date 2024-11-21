package app;
public class Level3Support extends SupportHandler {

    @Override
    public void handleRequest(SupportRequest request, javafx.scene.control.TextArea logArea) {
        if (request.getIssueType().equalsIgnoreCase("advanced")) {
            logArea.appendText("Level 3 Support: Handling advanced request -> " + request.getDescription() + "\n");
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request, logArea);
            } else {
                logArea.appendText("No handler found for the request: " + request.getDescription() + "\n");
            }
        }
    }
}
