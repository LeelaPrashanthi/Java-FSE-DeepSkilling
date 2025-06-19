public class WebApp implements Observer {
    private final String clientName;

    public WebApp(String clientName) {
        this.clientName = clientName;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println("WebApp [" + clientName + "]: " + stockSymbol + " price updated to $" + price);
    }
}
