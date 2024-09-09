package strategy;

import security.SecurityPersonnel;

public class StandardResponse implements ResponseStrategy {
    @Override
    public void respond(String alertDetails, SecurityPersonnel personnel) {
        // Add a blank line before the strategy log starts
        personnel.getHub().log("");  // Blank line to separate Mediator actions from Strategy actions

        personnel.getHub().log("[STRATEGY] " + personnel.getName() + " is responding with a standard response: " + alertDetails);
        personnel.getHub().log("[STRATEGY] Routine patrol or regular communication in progress.");
    }
}
