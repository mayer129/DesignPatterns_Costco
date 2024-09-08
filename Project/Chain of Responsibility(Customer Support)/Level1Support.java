// Level1Support.java
public class Level1Support extends SupportHandler {

    @Override
    public void handleRequest(SupportRequest request) {
        if (request.getIssueType().equalsIgnoreCase("basic")) {
            System.out.println("Level 1 Support: Handling basic request -> " + request.getDescription());
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.println("No handler found for the request: " + request.getDescription());
            }
        }
    }
}
