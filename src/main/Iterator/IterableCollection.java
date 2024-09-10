// IterableCollection.java
package Iterator;
public interface IterableCollection {
    Iterator<SupportTicket> createIterator(SupportTicket.Priority priority);
}
