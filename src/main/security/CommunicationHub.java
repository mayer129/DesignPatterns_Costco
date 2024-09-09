package security;

public interface CommunicationHub {
    void broadcastMessage(String message, SecurityPersonnel sender);
    void sendMessage(String message, SecurityPersonnel sender, SecurityPersonnel receiver);

    // Add this method for logging
    void log(String message);
}
