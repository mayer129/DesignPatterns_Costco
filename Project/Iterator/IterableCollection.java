// IterableCollection.java
public interface IterableCollection {
    Iterator<SupportTicket> createIterator(SupportTicket.Priority priority);
}
