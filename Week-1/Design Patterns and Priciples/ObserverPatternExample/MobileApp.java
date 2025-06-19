public class MobileApp implements Observer {
    private final String user;

    public MobileApp(String user) {
        this.user = user;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("MobileApp [" + user + "]: " + stockSymbol + " price updated to $" + price);
    }
}
