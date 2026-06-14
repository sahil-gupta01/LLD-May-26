
import java.util.HashMap;
import java.util.Map;

// Flyweight Design Pattern is a structural design pattern that allows you to share common data among multiple objects to save memory.
// It is particularly useful when you have a large number of similar objects that can share some of their state.

// In the Flyweight Design Pattern, we have the following components:
// 1. Flyweight: This is the interface that defines the common methods that all flyweight objects must implement. It typically includes methods for accessing the intrinsic state of the flyweight.
// 2. ConcreteFlyweight: This is the class that implements the Flyweight interface and represents the shared state of the flyweight. It contains the intrinsic state that can be shared among multiple objects.
// 3. FlyweightFactory: This is the class that creates and manages the flyweight objects. It ensures that flyweight objects are shared properly and provides a way to access them. 
//    The factory typically maintains a pool of flyweight objects and returns existing instances when requested, rather than creating new ones.

// Intrinsic state refers to the state that is shared among multiple objects, 
// while extrinsic state refers to the state that is unique to each object and cannot be shared. 
// The Flyweight pattern focuses on sharing the intrinsic state to reduce memory usage, while allowing the extrinsic state to be stored separately for each object.

// Example: Objects in a shooting game can be represented using the Flyweight design pattern.
// We can have a Bullet class that represents the intrinsic state of a bullet, such as its type and color. The extrinsic state, such as the position and velocity of the bullet, can be stored separately for each bullet instance.
// The BulletFactory class can be used to create and manage the Bullet instances, ensuring that bullets with the same type and color are shared among multiple instances to save memory.
// This way, we can have a large number of bullets in the game without consuming a lot of memory, as the intrinsic state is shared among them.
// It is typically used in scenarios where there are a large number of similar objects that can share some of their state, such as in graphical applications, games, or text processing systems.

// Code Example:

public class FlyweightDemo {
    public static void main(String[] args) {
        BulletFactory bulletFactory = new BulletFactory();

        // Create bullets with intrinsic state
        Bullet bullet1 = bulletFactory.getBullet("Pistol", "Black");
        Bullet bullet2 = bulletFactory.getBullet("Pistol", "Black");
        Bullet bullet3;
        bullet3 = bulletFactory.getBullet("Rifle", "Green");

        // Create moving bullets with extrinsic state
        MovingBullet movingBullet1 = new MovingBullet(bullet1, 0, 0);
        MovingBullet movingBullet2 = new MovingBullet(bullet2, 10, 10);
        MovingBullet movingBullet3 = new MovingBullet(bullet3, 20, 20);

        // Move the bullets
        movingBullet1.move(5, 5);
        movingBullet2.move(15, 15);
        movingBullet3.move(25, 25);

        // Print the moving bullets
        System.out.println(movingBullet1);
        System.out.println(movingBullet2);
        System.out.println(movingBullet3);
    }
}

// Flyweight class for intrinsic state that represents the intrinsic state of a bullet
class Bullet {
    private String type;
    private String color;

    public Bullet(String type, String color) {
        this.type = type;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Bullet{" +
                "type='" + type + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}

// Moving bullet (With extrinsic state) class that represents the extrinsic state of a bullet
class MovingBullet {
    private Bullet bullet; // Intrinsic state
    private int x; // Extrinsic state
    private int y; // Extrinsic state

    public MovingBullet(Bullet bullet, int x, int y) {
        this.bullet = bullet;
        this.x = x;
        this.y = y;
    }

    public void move(int deltaX, int deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }

    @Override
    public String toString() {
        return "MovingBullet{" +
                "bullet=" + bullet +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}



// FlyweightFactory class that creates and manages the Bullet instances
class BulletFactory {
    private Map<String, Bullet> bulletPool = new HashMap<>();

    public Bullet getBullet(String type, String color) {
        String key = type + "-" + color;
        if (!bulletPool.containsKey(key)) {
            bulletPool.put(key, new Bullet(type, color));
        }
        return bulletPool.get(key);
    }
}

// In this example, the Bullet class represents the intrinsic state of a bullet, which includes its type and color. 
// The BulletFactory class manages the creation of Bullet instances and ensures that bullets with the same type and color are shared among multiple instances. 
// When we request a bullet from the factory, it checks if a bullet with the specified type and color already exists in the pool. If it does, it returns the existing instance; 
// otherwise, it creates a new instance and adds it to the pool before returning it. This way, we can have multiple bullets in the game without consuming a lot of memory, as the intrinsic state is shared among them.

