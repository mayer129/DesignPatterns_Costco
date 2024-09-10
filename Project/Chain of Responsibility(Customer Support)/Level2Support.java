// Level2Support.java
public class Level2Support extends SupportHandler {

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getIssueType().equalsIgnoreCase("intermediate")) {
            System.out.println("Level 2 Support: Handling intermediate request -> " + request.getDescription());
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.println("No handler found for the request: " + request.getDescription());
            }
        }
    }
}
