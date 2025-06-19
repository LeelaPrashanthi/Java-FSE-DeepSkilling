public interface Stock {
    void registerObserver(Observer observer);
    void deregister(Observer observer);  // updated name
    void notifyObservers();
}
