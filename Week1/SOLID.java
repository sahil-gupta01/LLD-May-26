// SOLID -> There are certain principles that we followin while code implementation
// to make our code more maintainable, scalable and robust. These principles are:

// 1.SRP -> Single Responsibility Principle
// 2.OCP -> Open Closed Principle
// 3.LSP -> Liskov Substitution Principle
// 4.ISP -> Interface Segregation Principle
// 5.DIP -> Dependency Inversion Principle

// SRP -> A class/block of code should have only one reason to changes. 
// It should have only one responsibility. 
// If there are multiple responsibilities then it becomes difficult to maintain the code and also it becomes difficult to understand the code.

// Example of SRP violation in java

public boolean runQuery(String query) {
    try {
        DbConnnection dbConnection = new DbConnection();
        dbConnection.connect();
        dbConnection.executeQuery(query);
        dbConnection.close();
        return true;
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}

// In above example the method runQuery is doing multiple things. 
// It is creating a connection to the database, executing the query and then closing the connection. 
// This violates the SRP principle because if there is any change in the way we connect to the database or if there is any change in the way we execute the query, then we have to change the runQuery method.

// To resolve this issue, we can create a separate class for database connection and then we can have a method in that class that takes care of connecting to the database, executing the query and closing the connection. This way, if there is any change in the way we connect to the database or if there is any change in the way we execute the query, then we only have to change the method in the database connection class and not the runQuery method.
public class DbConnection {
    public void connect() {
        // code to connect to the database
    }

    public void executeQuery(String query) {
        // code to execute the query
    }

    public void close() {
        // code to close the connection
    }
}

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// OCP ->  A class should be open for extension but closed for modification. 
// In other words, we should be able to add new functionality to a class without changing the existing code. This can be achieved by using interfaces and abstract classes.

// Example of OCP violation in java
public double calculateArea(String shapeType, double dimension1, double dimension2) {
    if (shapeType.equals("Circle")) {
        return Math.PI * dimension1 * dimension1;
    } else if (shapeType.equals("Rectangle")) {
        return dimension1 * dimension2;
    } else {
        throw new IllegalArgumentException("Invalid shape type");
    }
}

// Above code violates ocp if we have a class that calculates the area of different shapes and we have a method that takes the shape type as a parameter and then calculates the area based on the shape type. This way, if we want to add a new shape, we have to change the existing code to accommodate the new shape type. This violates the OCP principle because we are changing the existing code to add new functionality.
// To resolve this issue, we can create an interface called Shape and then we can have different classes that implement the Shape interface for different shapes like Circle, Rectangle, etc. This way, if we want to add a new shape, we can simply create a new class that implements the Shape interface without changing the existing code.

public interface Shape {
    double calculateArea();
}
public class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// LSP -> Subtypes must be substitutable for their base types.
// In other words, if a class is a subclass of another class, then it should be able to replace the parent class without any issues.
// Or, if there is a method inside a class and we are overriding that method in the subclass, then the overridden method should be able to replace the parent method without any issues.

// Example of LSP violation in java
public class Bird {
    public void fly() {
        // code to fly
    }
}

public class Penguin extends Bird {
    @Override
    public void fly() {
        throw new UnsupportedOperationException("Penguins cannot fly");
    }
}

// method that takes Bird object as a parameter
public void makeBirdFly(Bird bird) {
    bird.fly(); // this will throw an exception if we pass a Penguin object to this method
}   

// Above code violates LSP because the Penguin class is a subclass of the Bird class and it overrides the fly method. 
// However, the fly method in the Penguin class throws an exception because penguins cannot fly. This means that if we have a method that takes a Bird object as a parameter and we pass a Penguin object to that method, then it will throw an exception. This violates the LSP principle because the Penguin class cannot replace the Bird class without any issues.

// To resolve this issue, we can create separate interfaces for flying and non-flying birds (ISP).
// We can have a Flyable interface that has a fly method and then we can have a NonFlyable interface that does not have a fly method. This way, we can have the Bird class implement the Flyable interface and the Penguin class implement the NonFlyable interface. This way, we can avoid the LSP violation because the Penguin class will not be able to replace the Bird class without any issues.

public interface Flyable {
    void fly();
}
public interface NonFlyable {
    // no fly method
}
public class Bird implements Flyable {
    @Override
    public void fly() {
        // code to fly
    }
}
public class Penguin implements NonFlyable {
    // Penguin does not implement fly method
}

// method that takes Flyable object as a parameter
public void makeBirdFly(Flyable bird) {
    bird.fly(); // birds that implement Flyable can fly without any issues
}

// ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// ISP -> Interface should be segregated on the basis of the different use cases. 
// In other words, we should not have a single interface that has all the methods. 
// We should have multiple interfaces that are specific to the different use cases. This way, we can avoid having a large interface that has many methods that are not relevant to all the classes that implement it.

// // Example of ISP violation in java
public interface Worker {
    void work();
    void eat();
}

public class HumanWorker implements Worker {
    @Override
    public void work() {
        // code to work
    }

    @Override
    public void eat() {
        // code to eat
    }
}
public class RobotWorker implements Worker {
    @Override
    public void work() {
        // code to work
    }

    @Override
    public void eat() {
        throw new UnsupportedOperationException("Robots do not eat");
    }
}

// Above code violates ISP because we have a Worker interface that has both work and eat methods.
// The HumanWorker class implements both methods and it works fine. However, the RobotWorker class implements the eat method but it throws an exception because robots do not eat. 
// This violates the ISP principle because the RobotWorker class is forced to implement a method that is not relevant to it.

// To resolve this issue, we can create separate interfaces for work and eat. We can have a Workable interface that has a work method and an Eatable interface that has an eat method. This way, we can have the HumanWorker class implement both interfaces and the RobotWorker class implement only the Workable interface. This way, we can avoid the ISP violation because the RobotWorker class will not be forced to implement a method that is not relevant to it.
public interface Workable {
    void work();
}
public interface Eatable {
    void eat();
}
public class HumanWorker implements Workable, Eatable {
    @Override
    public void work() {
        // code to work
    }

    @Override
    public void eat() {
        // code to eat
    }
}
public class RobotWorker implements Workable {
    @Override
    public void work() {
        // code to work
    }
}

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// DIP -> High level modules should not depend on low level modules. Both should depend on abstractions.
// In other words, we should depend on abstractions rather than concrete implementations. This can be achieved by using interfaces and abstract classes.
// No 2 concrete implementations should depend on each other. They should depend on abstractions. This way, we can avoid tight coupling between the classes and also we can achieve loose coupling between the classes.

// Example of DIP violation in java
public class MySQLDatabase {
    public void connect() {
        // code to connect to MySQL database
    }
}
public class UserRepository {
    private MySQLDatabase mySQLDatabase;

    public UserRepository() {
        this.mySQLDatabase = new MySQLDatabase();
    }

    public void getUserById(int id) {
        mySQLDatabase.connect();
        // code to get user by id from MySQL database
    }
}

// Above code violates DIP because the UserRepository class is a high level module that depends on the MySQLDatabase class which is a low level module. 
// This creates a tight coupling between the UserRepository class and the MySQLDatabase class. 
// Due to this tight coupling, if we want to change the database from MySQL to another database, then we have to change the UserRepository class as well. This violates the DIP principle because the high level module (UserRepository) is depending on a low level module (MySQLDatabase).

// To resolve this issue, we can create an interface called Database and then we can have the MySQLDatabase class implement the Database interface. This way, the UserRepository class will depend on the Database interface rather than the MySQLDatabase class. This way, if we want to change the database from MySQL to another database, then we only have to change the MySQLDatabase class and not the UserRepository class. This way, we can achieve loose coupling between the classes and also we can avoid tight coupling between the classes.
public interface Database {
    void connect();
}
public class MySQLDatabase implements Database {
    @Override
    public void connect() {
        // code to connect to MySQL database
    }
}
public class UserRepository {
    private Database database;

    public UserRepository(Database database) {
        this.database = database;
    }

    public void getUserById(int id) {
        database.connect();
        // code to get user by id from the database
    }
}

// Now, if we want to change the database from MySQL to another database, then we can simply create a new class that implements the Database interface and then we can pass an instance of that class to the UserRepository class without changing the UserRepository class. 
// This way, we can achieve loose coupling between the classes and also we can avoid tight coupling between the classes.
