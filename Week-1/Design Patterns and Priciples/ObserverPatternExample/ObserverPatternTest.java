public class ObserverPatternTest {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileUser1 = new MobileApp("Alice");
        Observer mobileUser2 = new MobileApp("Bob");
        Observer webUser1 = new WebApp("InvestorPro");

        stockMarket.registerObserver(mobileUser1);
        stockMarket.registerObserver(mobileUser2);
        stockMarket.registerObserver(webUser1);

        stockMarket.setStockPrice("AAPL", 185.75);
        System.out.println("---");

        stockMarket.deregister(mobileUser2);  // updated method call
        stockMarket.setStockPrice("AAPL", 188.20);
    }
}
