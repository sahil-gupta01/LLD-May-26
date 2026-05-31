package FactoryDP;

// Factory design pattern is a creational design pattern.
// The Factory pattern is particularly useful when the exact type of object to be created is not known until runtime, or when the creation process involves complex logic that should be encapsulated in a separate class.
// The Factory pattern promotes loose coupling between the client code and the concrete classes that implement the objects being created, making it easier to maintain and extend the codebase over time.

// Example:
// Define an interface for the product
interface SFProduct {
    void use();
}
// Create concrete implementations of the product interface
class SFConcreteProductA implements SFProduct {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductA");
    }
}
class SFConcreteProductB implements SFProduct {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductB");
    }
}

// Define a factory interface using which we can create objects of the concrete classes
interface SFFactory {
    SFProduct createProduct(String type);
}
// Implement the factory interface
class SFConcreteFactory implements SFFactory {
    @Override
    public SFProduct createProduct(String type) {
        if (type.equalsIgnoreCase("A")) {
            return new SFConcreteProductA();
        } else if (type.equalsIgnoreCase("B")) {
            return new SFConcreteProductB();
        }
        throw new IllegalArgumentException("Unknown product type: " + type);
    }
}
// Client code that uses the factory pattern to create products without specifying the exact class of object that will be created
class SimpleFactoryDP {
    public static void main(String[] args) {
        SFFactory factory = new SFConcreteFactory();
        SFProduct productA = factory.createProduct("A");
        SFProduct productB = factory.createProduct("B");
        productA.use();
        productB.use();
    }
}

// But in simple factory pattern we have only one factory class that creates the objects based on the input parameter, and it does not follow the open-closed principle 
// because if we want to add a new product type we need to modify the existing factory class which can lead to bugs and maintenance issues.
// To fix this issue we can use the FACTORY METHOD PATTERN which allows us to create objects without specifying the exact class of object that will be created, and it follows the open-closed principle because we can add new product types by creating new factory classes without modifying the existing code.