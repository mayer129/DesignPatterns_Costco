package security;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;

public class WalkieTalkieHub implements CommunicationHub {
    private List<SecurityPersonnel> personnelList = new ArrayList<>();
    private TextArea logArea;  // TextArea for logging actions

    // Constructor that takes the TextArea as a parameter
    public WalkieTalkieHub(TextArea logArea) {
        this.logArea = logArea;
    }

    // Register a security personnel in the hub
    public void registerSecurity(SecurityPersonnel security) {
        personnelList.add(security);
        log("[MEDIATOR] " + security.getName() + " has joined the walkie-talkie network.");

        // Add a blank line after all personnel have joined
        if (personnelList.size() == 4) {  // Assuming 4 personnel join
            log("");  // Adds a blank line to separate the join logs from the rest
        }
    }

    // Broadcast a message to all except the sender
    @Override
    public void broadcastMessage(String message, SecurityPersonnel sender) {
        log("[MEDIATOR] " + sender.getName() + " is broadcasting a message: " + message);
        for (SecurityPersonnel security : personnelList) {
            if (security != sender) {
                security.receiveMessage(message, sender);  // Just send the message
            }
        }
    }

    // Send a message to a specific receiver
    @Override
    public void sendMessage(String message, SecurityPersonnel sender, SecurityPersonnel receiver) {
        log("[MEDIATOR] " + sender.getName() + " is sending a private message to " + receiver.getName() + ": " + message);
        receiver.receiveMessage(message, sender);
    }

    // Get a list of all registered personnel
    public List<SecurityPersonnel> getAllPersonnel() {
        return personnelList;  // Return the list of all registered personnel
    }

    // Implement the log method using Platform.runLater to update the UI thread
    @Override
    public void log(String message) {
        Platform.runLater(() -> logArea.appendText(message + "\n"));
    }
}
