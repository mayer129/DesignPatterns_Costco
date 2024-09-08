// IteratorDemo.java
public class Main {
    public static void main(String[] args) {
        // Create a collection of support tickets
        TicketCollection ticketCollection = new TicketCollection();

        // Add tickets with varying priorities
        ticketCollection.addTicket(new SupportTicket("Password reset", SupportTicket.Priority.LOW));
        ticketCollection.addTicket(new SupportTicket("Billing issue", SupportTicket.Priority.HIGH));
        ticketCollection.addTicket(new SupportTicket("Technical problem", SupportTicket.Priority.MEDIUM));
        ticketCollection.addTicket(new SupportTicket("Membership renewal", SupportTicket.Priority.LOW));
        ticketCollection.addTicket(new SupportTicket("Refund issue", SupportTicket.Priority.HIGH));

        // Create iterators based on priority
        Iterator<SupportTicket> highPriorityIterator = ticketCollection.createIterator(SupportTicket.Priority.HIGH);
        Iterator<SupportTicket> lowPriorityIterator = ticketCollection.createIterator(SupportTicket.Priority.LOW);

        // Process tickets with HIGH priority
        System.out.println("Processing HIGH priority tickets:");
        while (highPriorityIterator.hasNext()) {
            SupportTicket ticket = highPriorityIterator.next();
            System.out.println(ticket);
        }

        // Process tickets with LOW priority
        System.out.println("\nProcessing LOW priority tickets:");
        while (lowPriorityIterator.hasNext()) {
            SupportTicket ticket = lowPriorityIterator.next();
            System.out.println(ticket);
        }
    }
}
