package bakery;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


public class OrderCaretaker {
    private final Map<Long, Stack<OrderMemento>> mementos = new HashMap<>();

    public void addMemento(long orderNumber, OrderMemento memento) {
        mementos.computeIfAbsent(orderNumber, k -> new Stack<>()).push(memento);
    }

    public OrderMemento getMemento(long orderNumber) {
        Stack<OrderMemento> orderMementos = mementos.get(orderNumber);
        if (orderMementos != null && !orderMementos.isEmpty()) {
            return orderMementos.pop();
        }
        return null;
    }
}