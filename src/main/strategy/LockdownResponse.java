package strategy;

import security.SecurityPersonnel;

public class LockdownResponse implements ResponseStrategy {
    @Override
    public void respond(String alertDetails, SecurityPersonnel personnel) {
        // Add a blank line before the strategy log starts
        personnel.getHub().log("");  // Blank line to separate Mediator actions from Strategy actions

        personnel.getHub().log("[STRATEGY] " + personnel.getName() + " is initiating lockdown response: " + alertDetails);
        personnel.getHub().log("[STRATEGY] Lockdown protocol activated. Securing all entry and exit points.");
        personnel.getHub().log("[STRATEGY] Locking down the store.");
    }
}
