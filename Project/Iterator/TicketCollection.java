// TicketCollection.java
import java.util.ArrayList;
import java.util.List;

public class TicketCollection implements IterableCollection {
    private List<SupportTicket> tickets;

    public TicketCollection() {
        tickets = new ArrayList<>();
    }

    public void addTicket(SupportTicket ticket) {
        tickets.add(ticket);
    }

    @Override
    public Iterator<SupportTicket> createIterator(SupportTicket.Priority priority) {
        return new PriorityIterator(this, priority);
    }

    public List<SupportTicket> getTickets() {
        return tickets;
    }
}
