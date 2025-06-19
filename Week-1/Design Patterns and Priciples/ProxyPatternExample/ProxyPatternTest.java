public class ProxyPatternTest {
    public static void main(String[] args) {
        Image image1 = new ProxyImage("nature.jpg");
        Image image2 = new ProxyImage("city.jpg");
        Image image3 = new ProxyImage("nature.jpg");

        // First time loading from remote
        image1.display();

        // First time loading from remote
        image2.display();

        // Second time should use cache
        image3.display();
    }
}
