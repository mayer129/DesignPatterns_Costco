// Level3Support.java
public class Level3Support extends SupportHandler {

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getIssueType().equalsIgnoreCase("advanced")) {
            System.out.println("Level 3 Support: Handling advanced request -> " + request.getDescription());
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.println("No handler found for the request: " + request.getDescription());
            }
        }
    }
}
