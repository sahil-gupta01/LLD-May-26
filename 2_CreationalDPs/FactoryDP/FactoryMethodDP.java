package FactoryDP;

// In the factory method pattern, we define an interface for creating an object, but let subclasses decide which class to instantiate. The factory method lets a class defer instantiation to subclasses.

// Example of factory method pattern:

// Define an interface for the product
interface FMProduct {
    void use();
}

// Create concrete implementations of the product interface
class FMConcreteProductA implements FMProduct {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductA");
    }
}
class FMConcreteProductB implements FMProduct {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductB");
    }
}
// Define a factory interface using which we can create objects of the concrete classes
interface FMFactory {
    FMProduct createProduct();
}
// Implement the factory interface for ConcreteProductA
class FMConcreteFactoryA implements FMFactory {
    @Override
    public FMProduct createProduct() {
        return new FMConcreteProductA();
    }
}
// Implement the factory interface for ConcreteProductB
class FMConcreteFactoryB implements FMFactory {
    @Override
    public FMProduct createProduct() {
        return new FMConcreteProductB();
    }
}


// Client code that uses the factory method pattern to create products without specifying the exact class of object that will be created
public class FactoryMethodDP {
    public static void main(String[] args) {
        FMFactory factoryA = new FMConcreteFactoryA();
        FMFactory factoryB = new FMConcreteFactoryB();
        FMProduct productA = factoryA.createProduct();
        FMProduct productB = factoryB.createProduct();
        productA.use();
        productB.use();
    }
}

// Now if we want to add a new product type we can simply create a new factory class that implements the Factory interface without modifying the existing code, which follows the open-closed principle and makes our code more maintainable and extensible.
class FMConcreteProductC implements FMProduct {
    @Override
    public void use() {
        System.out.println("Using ConcreteProductC");
    }
}
class FMConcreteFactoryC implements FMFactory {
    @Override
    public FMProduct createProduct() {
        return new FMConcreteProductC();
    }
}

// The simple factory pattern is a basic implementation of the factory design pattern that provides a single factory class to create objects based on input parameters, 
// while the factory method pattern is a more flexible and extensible implementation that allows for multiple factory classes to create different types of objects without modifying existing code.

