// PriorityIterator.java
package Iterator;
import java.util.List;

public class PriorityIterator implements Iterator<SupportTicket> {
    private List<SupportTicket> tickets;
    private int position;
    private SupportTicket.Priority priority;

    public PriorityIterator(TicketCollection collection, SupportTicket.Priority priority) {
        this.tickets = collection.getTickets();
        this.priority = priority;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        while (position < tickets.size()) {
            if (tickets.get(position).getPriority() == priority) {
                return true;
            }
            position++;
        }
        return false;
    }

    @Override
    public SupportTicket next() {
        SupportTicket ticket = tickets.get(position);
        position++;
        return ticket;
    }
}
