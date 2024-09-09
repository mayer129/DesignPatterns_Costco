public class Order {
    private static long orderCounter = 0;
    private long orderNumber;
    private Cake cake;
    private String status;

    public Order(Cake cake) {
        this.orderNumber = ++orderCounter;
        this.cake = cake;
        this.status = "Queued";
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public long getOrderNumber() {
        return orderNumber;
    }

    public OrderMemento saveState() {
        return new OrderMemento(status);
    }

    public void restoreState(OrderMemento memento) {
        this.status = memento.getStatus();
    }

    @Override
    public String toString() {
        return "Order #" + orderNumber + ": " + cake + " - Status: " + status;
    }
}