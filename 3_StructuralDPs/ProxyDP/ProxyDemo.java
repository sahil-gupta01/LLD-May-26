package ProxyDP;

// Proxy Design Pattern: A structural design pattern that provides a surrogate or placeholder for another object to control access to it. The proxy object acts as an intermediary, 
// allowing you to add additional functionality or control access to the original object without modifying its code.   

// In the Proxy Design Pattern, we have the following components:
// 1. Subject: This is the interface that defines the common methods that both the RealSubject and the Proxy will implement. It typically includes methods that the client will use to interact with the Real Subject.
// 2. RealSubject: This is the class that implements the Subject interface and represents the actual object that the proxy will control access to. It contains the real implementation of the methods defined in the Subject interface.
// 3. Proxy: This is the class that implements the Subject interface and contains a reference to the RealSubject. 
//    The Proxy class controls access to the RealSubject and can add additional functionality before or after delegating the call to the RealSubject.

// Example: A great example of Proxy design pattern is a virtual proxy for loading images in a web application.
// We can have an Image interface (Subject) that defines the common methods for both the Real
// Image class (RealSubject) and the ImageProxy class (Proxy). The RealImage class will represent the actual image that needs to be loaded, while the ImageProxy class will control access to the RealImage and load it only when necessary.
// This way, we can improve the performance of the web application by loading images only when they are needed, rather than loading all images at once. The ImageProxy class can also add additional functionality such as caching the loaded images to further improve performance.

// Code Example:
public class ProxyDemo {
    public static void main(String[] args) {
        Image image1 = new ImageProxy("image1.jpg");
        Image image2 = new ImageProxy("image2.jpg");

        // Load the images
        image1.display();
        image2.display();
        image1.display(); // This will use the already loaded image1.jpg
        image2.display(); // This will use the already loaded image2.jpg
    }
}

// Subject interface that defines the common methods for both the RealSubject and the Proxy
interface Image {
    void display();
}

// RealSubject class that represents the actual object that the proxy will control access to
class RealImage implements Image {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }

    private void loadFromDisk() {
        System.out.println("Loading " + fileName);
    }

    @Override
    public void display() {
        System.out.println("Displaying " + fileName);
    }
}

// Proxy class that controls access to the RealImage and loads it only when necessary
class ImageProxy implements Image {
    private String fileName;
    private RealImage realImage;

    public ImageProxy(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(fileName);
        }
        realImage.display();
    }
}

// In this example, the ImageProxy class acts as a virtual proxy for the RealImage class. It controls access to the RealImage and loads it only when the display() method is called for the first time. 
// Subsequent calls to display() will use the already loaded RealImage, improving performance by avoiding unnecessary loading of images.