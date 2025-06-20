public class StrategyPatternTest {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay with Credit Card
        context.setPaymentStrategy(new CreditCardPayment("1234-5678-9012-3456", "John Doe", "123"));
        context.processPayment(250.00);

        // Pay with PayPal
        context.setPaymentStrategy(new PayPalPayment("john@example.com"));
        context.processPayment(150.00);
    }
}  
