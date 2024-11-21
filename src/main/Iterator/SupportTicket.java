// SupportTicket.java
package Iterator;
public class SupportTicket {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    private String description;
    private Priority priority;

    public SupportTicket(String description, Priority priority) {
        this.description = description;
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    @Override
    public String toString() {
        return "Ticket: " + description + " [Priority: " + priority + "]";
    }
}
