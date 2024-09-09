package payment;
import java.util.concurrent.ConcurrentLinkedQueue;


public class PaymentProcessorPool {
    private ConcurrentLinkedQueue<PaymentProcessor> availableProcessors;
    private int poolSize;
    private static PaymentProcessorPool instance;

    // Private constructor for singleton pattern
    private PaymentProcessorPool(int poolSize) {
        this.poolSize = poolSize;
        availableProcessors = new ConcurrentLinkedQueue<>();
        
        // Initialize the pool with payment processors
        for (int i = 0; i < poolSize; i++) {
            availableProcessors.add(new CreditCardPaymentProcessor());
            System.out.println("Created initial PaymentProcessor #" + (i + 1));
        }
    }

    // Get the singleton instance
    public static synchronized PaymentProcessorPool getInstance(int poolSize) {
        if (instance == null) {
            instance = new PaymentProcessorPool(poolSize);
        }
        return instance;
    }

    // Borrow a processor from the pool
    public PaymentProcessor borrowProcessor() {
        PaymentProcessor processor = availableProcessors.poll();
        if (processor == null) {
            // Pool is exhausted, create a new processor
            System.out.println("Pool exhausted! Creating a new PaymentProcessor.");
            processor = new CreditCardPaymentProcessor();
        } else {
            System.out.println("Borrowing a PaymentProcessor from the pool.");
        }
        return processor;
    }

    // Return a processor back to the pool
    public void returnProcessor(PaymentProcessor processor) {
        System.out.println("Returning a PaymentProcessor back to the pool.");
        availableProcessors.offer(processor);
    }

    // Check how many processors are currently in the pool
    public int availableProcessors() {
        return availableProcessors.size();
    }
}
