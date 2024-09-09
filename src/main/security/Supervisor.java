package security;

public class Supervisor extends SecurityPersonnel {

    public Supervisor(String name, CommunicationHub hub) {
        super(name, hub);
    }

    @Override
    public void receiveMessage(String message, SecurityPersonnel sender) {
        // Just log the received message, don't call broadcastMessage() again
        hub.log(this.name + " (Supervisor) received a message from " + sender.getName() + ": " + message);
    }
}
