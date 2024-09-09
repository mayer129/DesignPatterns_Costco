package payment;

public interface PaymentProcessor {
    boolean processPayment(double amount, String customerDetails, String paymentDetails);
}
