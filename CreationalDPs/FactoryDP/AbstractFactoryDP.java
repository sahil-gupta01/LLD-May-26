package FactoryDP;

// Abstract Factory design pattern is a creational design pattern that provides an interface for creating families of related or dependent objects without specifying their concrete classes.
// In simple words, abstract factory pattern is a factory of factories. It allows us to create a factory that can create multiple types of products, and the client code can use the abstract factory to create products without knowing the exact class of object that will be created.
// For example, we can have an abstract factory that creates UI components for different platforms (like Windows, Android, iOS) and the client code can use the abstract factory to create UI components without knowing the exact class of object that will be created.

// Define interfaces for the products
interface Button {
    void use();
}
interface Checkbox {
    void use();
}
// Create concrete implementations of the product interfaces for different platforms
class AndroidButton implements Button {
    @Override
    public void use() {
        System.out.println("Using Android Button");
    }
}
class AndroidCheckbox implements Checkbox {
    @Override
    public void use() {
        System.out.println("Using Android Checkbox");
    }
}
class WindowsButton implements Button {
    @Override
    public void use() {
        System.out.println("Using Windows Button");
    }
}
class WindowsCheckbox implements Checkbox {
    @Override
    public void use() {
        System.out.println("Using Windows Checkbox");
    }
}
// Define an abstract factory interface using which we can create objects of the concrete classes
interface UIAbstractFactory {
    Button createButton();
    Checkbox createCheckbox();
}
// Implement the abstract factory interface for Windows platform
class UIConcreteFactoryWindows implements UIAbstractFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}
// Implement the abstract factory interface for Android platform
class UIConcreteFactoryAndroid implements UIAbstractFactory {
    @Override
    public Button createButton() {
        return new AndroidButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new AndroidCheckbox();
    }
}
// Client code that uses the abstract factory pattern to create products without specifying the exact class of object that will be created
class AbstractFactoryDP {
    public static void main(String[] args) {
        UIAbstractFactory factoryWindows = new UIConcreteFactoryWindows();
        UIAbstractFactory factoryAndroid = new UIConcreteFactoryAndroid();
        Button button1 = factoryWindows.createButton();
        Checkbox checkbox1 = factoryWindows.createCheckbox();
        Button button2 = factoryAndroid.createButton();
        Checkbox checkbox2 = factoryAndroid.createCheckbox();
        button1.use();
        checkbox1.use();
        button2.use();
        checkbox2.use();
    }
}

// Now if we want to add a new platform (like iOS) we can simply create a new factory class that implements the Abstract Factory interface without modifying the existing code, which follows the open-closed principle and makes our code more maintainable and extensible.
class IOSButton implements Button {
    @Override
    public void use() {
        System.out.println("Using iOS Button");
    }
}
class IOSCheckbox implements Checkbox {
    @Override
    public void use() {
        System.out.println("Using iOS Checkbox");
    }
}

// Implement the abstract factory interface for iOS platform
class UIConcreteFactoryIOS implements UIAbstractFactory {
    @Override
    public Button createButton() {
        return new IOSButton();
    }
    @Override
    public Checkbox createCheckbox() {
        return new IOSCheckbox();
    }
}
