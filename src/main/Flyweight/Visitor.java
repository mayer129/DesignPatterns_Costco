// Visitor.java
//The Visitor interface defines the visit method
package main.Flyweight;
//that each visitor (report generator, discount applier) will implement.
public interface Visitor {
    void visit(StockItem stockItem);
}
