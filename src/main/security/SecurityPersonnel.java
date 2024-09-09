package security;

import strategy.ResponseStrategy;

public abstract class SecurityPersonnel {
    protected String name;
    protected CommunicationHub hub;
    protected ResponseStrategy responseStrategy;

    public SecurityPersonnel(String name, CommunicationHub hub) {
        this.name = name;
        this.hub = hub;
    }

    public String getName() {
        return name;
    }

    public CommunicationHub getHub() {
        return hub;
    }

    public void setResponseStrategy(ResponseStrategy responseStrategy) {
        this.responseStrategy = responseStrategy;
    }

    public void handleSecurityAlert(String alertDetails) {
        responseStrategy.respond(alertDetails, this);
    }

    public void broadcast(String message) {
        hub.broadcastMessage(message, this);
        handleSecurityAlert(message);
    }

    public void sendPrivateMessage(String message, SecurityPersonnel receiver) {
        hub.sendMessage(message, this, receiver);
        handleSecurityAlert(message);
    }

    public abstract void receiveMessage(String message, SecurityPersonnel sender);

    // Override toString() to return the name of the personnel
    @Override
    public String toString() {
        return name;
    }
}
