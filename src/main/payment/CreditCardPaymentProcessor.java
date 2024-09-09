package payment;
public class CreditCardPaymentProcessor implements PaymentProcessor {
    @Override
    public boolean processPayment(double amount, String customerDetails, String paymentDetails) {
        // Simulate payment processing logic (interact with payment gateway, etc.)
        System.out.println("Processing payment of $" + amount + " for " + customerDetails);
        try {
            // Simulate network latency or processing delay
            Thread.sleep(1000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;  // Simulate successful payment
    }
}
