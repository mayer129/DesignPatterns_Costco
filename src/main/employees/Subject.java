// Subject Interface
package main.employees;

import main.observers.Observer;

public interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String itemName);
}
