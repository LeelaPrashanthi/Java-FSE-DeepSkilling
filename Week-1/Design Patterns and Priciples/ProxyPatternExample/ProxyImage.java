import java.util.HashMap;
import java.util.Map;

public class ProxyImage implements Image {
    private final String filename;
    private static final Map<String, RealImage> imageCache = new HashMap<>();

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        RealImage realImage = imageCache.get(filename);

        if (realImage == null) {
            realImage = new RealImage(filename);
            imageCache.put(filename, realImage);
        } else {
            System.out.println("Using cached image: " + filename);
        }

        realImage.display();
    }
}
