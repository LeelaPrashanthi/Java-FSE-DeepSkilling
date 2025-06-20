public class CreditCardPayment implements PaymentStrategy {
    private final String cardNumber;
    private final String cardHolder;
    private final String cvv;

    public CreditCardPayment(String cardNumber, String cardHolder, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card [" + cardNumber + "]");
    }
}
