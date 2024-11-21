package strategy;

import security.SecurityPersonnel;

public interface ResponseStrategy {
    void respond(String alertDetails, SecurityPersonnel personnel);
}
