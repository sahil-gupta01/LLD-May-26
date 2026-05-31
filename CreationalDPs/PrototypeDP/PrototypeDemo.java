package PrototypeDP;

// Prototype design pattern is a creational design pattern that allows us to create new objects 
// by copying existing objects, rather than creating new instances from scratch. 
// This can be useful when creating new objects is expensive or time-consuming, or when we want 
// to create multiple copies of an object with the same state.

// Example:

// Define an interface for the prototype
interface Prototype {
    Prototype clone();
}
// Create a concrete implementation of the prototype interface
class ConcretePrototype implements Prototype {
    private String name;
    private int value;

    public ConcretePrototype(String name, int value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.name, this.value);
    }

    @Override
    public String toString() {
        return "ConcretePrototype{name='" + name + "', value=" + value + "}";
    }
}

// Client code that uses the prototype pattern to create new objects by cloning existing objects
class PrototypeDemo {
    public static void main(String[] args) {
        ConcretePrototype original = new ConcretePrototype("Original", 42);
        System.out.println("Original: " + original + " at " + System.identityHashCode(original));

        ConcretePrototype clone = (ConcretePrototype) original.clone();
        System.out.println("Clone: " + clone + " at " + System.identityHashCode(clone));
    }
} 

// In this example, we have defined a Prototype interface with a clone() method, 
// and a ConcretePrototype class that implements this interface. The clone() method creates a new instance of ConcretePrototype
// with the same state as the original object. In the client code, we create an original object and then clone it to create a new object with the same state.            

