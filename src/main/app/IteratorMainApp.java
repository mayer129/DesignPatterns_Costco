package app;

import Iterator.TicketCollection;
import Iterator.SupportTicket;
import Iterator.Iterator;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class IteratorMainApp extends Application {

    private TicketCollection ticketCollection;
    private TextArea logArea;

    public static void main(String[] args) {
        launch(args);  // Launch JavaFX application
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Iterator Demo");

        // Initialize the ticket collection
        ticketCollection = new TicketCollection();

        // Add tickets with varying priorities
        ticketCollection.addTicket(new SupportTicket("Password reset", SupportTicket.Priority.LOW));
        ticketCollection.addTicket(new SupportTicket("Billing issue", SupportTicket.Priority.HIGH));
        ticketCollection.addTicket(new SupportTicket("Technical problem", SupportTicket.Priority.MEDIUM));
        ticketCollection.addTicket(new SupportTicket("Membership renewal", SupportTicket.Priority.LOW));
        ticketCollection.addTicket(new SupportTicket("Refund issue", SupportTicket.Priority.HIGH));

        // UI components
        logArea = new TextArea();
        logArea.setEditable(false);  // Make the log area read-only

        Button processHighPriorityBtn = new Button("Process HIGH Priority Tickets");
        Button processLowPriorityBtn = new Button("Process LOW Priority Tickets");
        Button exitBtn = new Button("Exit");

        // VBox layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.getChildren().addAll(processHighPriorityBtn, processLowPriorityBtn, logArea, exitBtn);

        // Button actions
        processHighPriorityBtn.setOnAction(event -> processHighPriorityTickets());
        processLowPriorityBtn.setOnAction(event -> processLowPriorityTickets());
        exitBtn.setOnAction(event -> Platform.exit());

        // Set the scene and show the stage
        Scene scene = new Scene(layout, 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to process HIGH priority tickets
    private void processHighPriorityTickets() {
        logArea.appendText("Processing HIGH priority tickets:\n");
        Iterator<SupportTicket> highPriorityIterator = ticketCollection.createIterator(SupportTicket.Priority.HIGH);
        while (highPriorityIterator.hasNext()) {
            SupportTicket ticket = highPriorityIterator.next();
            logArea.appendText(ticket.toString() + "\n");
        }
        logArea.appendText("\n");
    }

    // Method to process LOW priority tickets
    private void processLowPriorityTickets() {
        logArea.appendText("Processing LOW priority tickets:\n");
        Iterator<SupportTicket> lowPriorityIterator = ticketCollection.createIterator(SupportTicket.Priority.LOW);
        while (lowPriorityIterator.hasNext()) {
            SupportTicket ticket = lowPriorityIterator.next();
            logArea.appendText(ticket.toString() + "\n");
        }
        logArea.appendText("\n");
    }
}
