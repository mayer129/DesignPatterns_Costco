// SupportChainDemo.java
public class Main {
    public static void main(String[] args) {
        // Create handlers
        SupportHandler level1Support = new Level1Support();
        SupportHandler level2Support = new Level2Support();
        SupportHandler level3Support = new Level3Support();

        // Set up the chain
        level1Support.setNextHandler(level2Support);
        level2Support.setNextHandler(level3Support);

        // Create support requests
        SupportRequest basicRequest = new SupportRequest("basic", "Password reset.");
        SupportRequest intermediateRequest = new SupportRequest("intermediate", "Issue with billing.");
        SupportRequest advancedRequest = new SupportRequest("advanced", "Technical issue with online orders.");

        // Process requests
        System.out.println("Processing Basic Request:");
        level1Support.handleRequest(basicRequest);

        System.out.println("\nProcessing Intermediate Request:");
        level1Support.handleRequest(intermediateRequest);

        System.out.println("\nProcessing Advanced Request:");
        level1Support.handleRequest(advancedRequest);
    }
}
