package strategy;

import security.SecurityPersonnel;

public class EmergencyResponse implements ResponseStrategy {
    @Override
    public void respond(String alertDetails, SecurityPersonnel personnel) {
        // Add a blank line before the strategy log starts
        personnel.getHub().log("");  // Blank line to separate Mediator actions from Strategy actions

        personnel.getHub().log("[STRATEGY] " + personnel.getName() + " is responding to an emergency: " + alertDetails);
        personnel.getHub().log("[STRATEGY] Alerting all personnel and notifying supervisors immediately.");
        personnel.getHub().log("[STRATEGY] Taking immediate action to contain the situation.");
    }
}
