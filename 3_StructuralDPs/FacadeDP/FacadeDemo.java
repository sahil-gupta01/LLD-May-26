// Facade Design Pattern: Provides a simplified interface to a complex subsystem.
// In other words, it hides the complexities of the system and provides an easy-to-use interface for the client.
// The Facade design pattern is used when we want to provide a simple interface to a complex subsystem. 
// It is typically used to simplify the interactions between the client and the subsystem, making it easier for the client to use the subsystem without needing to understand its complexities. 

// In the Facade Design Pattern, we have the following components:
// 1. Facade: This is the class that provides a simplified interface to the complex subsystem. It contains a reference to the subsystem and delegates the client requests to the appropriate subsystem classes.
// 2. Subsystem: These are the classes that implement the complex subsystem. They contain the actual implementation of the functionalities that the Facade class will delegate to. The client interacts with the Facade class, which in turn interacts with the subsystem classes to fulfill the client's requests.

// Example: A great example of Facade design pattern is a home theater system.
// We can have a HomeTheaterFacade class that provides a simple interface to control various components
// of the home theater system such as TV, Sound System, DVD Player, etc. The client can interact with the HomeTheaterFacade class to control the entire home theater system without needing to understand the complexities of each individual component.

public class FacadeDemo {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}

// Facade class that provides a simplified interface to the complex subsystem
class HomeTheaterFacade {
    private TV tv;
    private SoundSystem soundSystem;
    private DVDPlayer dvdPlayer;

    public HomeTheaterFacade() {
        this.tv = new TV();
        this.soundSystem = new SoundSystem();
        this.dvdPlayer = new DVDPlayer();
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        tv.on();
        soundSystem.on();
        dvdPlayer.on();
        dvdPlayer.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting down the home theater...");
        dvdPlayer.off();
        soundSystem.off();
        tv.off();
    }
}

// Subsystem classes that implement the complex subsystem
class TV {
    public void on() {
        System.out.println("Turning on the TV");
    }

    public void off() {
        System.out.println("Turning off the TV");
    }
}

// Subsystem classes that implement the complex subsystem
class SoundSystem {
    public void on() {
        System.out.println("Turning on the Sound System");
    }

    public void off() {
        System.out.println("Turning off the Sound System");
    }
}

// Subsystem classes that implement the complex subsystem
class DVDPlayer {
    public void on() {
        System.out.println("Turning on the DVD Player");
    }

    public void off() {
        System.out.println("Turning off the DVD Player");
    }

    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }
}

// In this example, the HomeTheaterFacade class provides a simplified interface to control the TV, Sound System, and DVD Player. 
// The client can simply call the watchMovie() and endMovie() methods on the HomeTheaterFacade class without needing to understand how each individual component works. 
// This demonstrates the Facade Design Pattern in action.
