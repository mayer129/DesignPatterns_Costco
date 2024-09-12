package main.strategy;

import main.security.SecurityPersonnel;

public interface ResponseStrategy {
    void respond(String alertDetails, SecurityPersonnel personnel);
}
