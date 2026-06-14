// Decorator Design Pattern is a structural design pattern that allows behavior to be added to individual objects, either statically or dynamically, 
// without affecting the behavior of other objects from the same class. It is typically used to extend the functionalities of classes in a flexible and reusable way.

// In the Decorator Design Pattern, we have the following components:
// 1. Component: This is the interface or abstract class that defines the common behavior for both the concrete components and decorators.
// 2. Concrete Component: This is the class that implements the Component interface and represents the core functionality that can be decorated.
// 3. Decorator: This is an abstract class that implements the Component interface and contains a reference to a Component object. 
//    It serves as the base class for all decorators and can add additional behavior before or after delegating the call to the wrapped Component object.
// 4. Concrete Decorators: These are classes that extend the Decorator class and add specific functionalities to the Component object.

// A great example of Decorator design pattern is pizza ordering system.
// We can have a base pizza class (Concrete Component) and various toppings (Concrete Decorators) that can be added to the pizza dynamically without changing the base pizza class.

// Component
interface Pizza {
    String getDescription();
    double getCost();
}

// Concrete Component
class MargheritaPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Margherita Pizza";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// Another Concrete Component
class ThinCrustPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Thin Crust Pizza";
    }

    @Override
    public double getCost() {
        return 6.0;
    }
}

// Decorator
abstract class ToppingDecorator implements Pizza {
    protected Pizza pizza;

    public ToppingDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription();
    }

    @Override
    public double getCost() {
        return pizza.getCost();
    }
}

// Concrete Decorator
class CheeseTopping extends ToppingDecorator {
    public CheeseTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.0;
    }
}

// Another Concrete Decorator
class PepperoniTopping extends ToppingDecorator {
    // we can add validation to check there should be a pizza object to add the topping, if not we can throw an exception
    // we can also add a limit to the number of toppings that can be added to a pizza, if the limit is exceeded we can throw an exception
    // validation can be added in the constructor of the decorator class or in the getDescription() and getCost() methods of the decorator class

    public PepperoniTopping(Pizza pizza) {
        super(validatePizza(pizza));
    }

    private static Pizza validatePizza(Pizza pizza) {
        // validation to check if pizza object is null
        if (pizza == null) {
            throw new IllegalArgumentException("Pizza object cannot be null");
        }

        String description = pizza.getDescription();

        // validation to check if the pizza object already has a pepperoni topping, if yes then throw an exception
        if (description.contains("Pepperoni")) {
            throw new IllegalArgumentException("Pizza already has Pepperoni topping");
        }

        // validation to check if the pizza object already has a limit of toppings, if yes then throw an exception
        if (description.split(",").length >= 5) {
            throw new IllegalArgumentException("Pizza has reached the maximum number of toppings");
        }

        return pizza;
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 1.5;
    }
}

// Another Concrete Decorator
class OlivesTopping extends ToppingDecorator {
    public OlivesTopping(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return pizza.getDescription() + ", Olives";
    }

    @Override
    public double getCost() {
        return pizza.getCost() + 0.5;
    }
}

// Now we can use the above classes to create different types of pizzas with various toppings without modifying the existing pizza classes. 
// This demonstrates the flexibility and reusability of the Decorator Design Pattern.

public class DecoratorDemo {
    public static void main(String[] args) {
        // Creating a Margherita Pizza
        Pizza pizza = new MargheritaPizza();
        System.out.println(pizza.getDescription() + "\nCost: $" + pizza.getCost());

        pizza = new CheeseTopping(pizza);
        System.out.println(pizza.getDescription() + "\nCost: $" + pizza.getCost());

        pizza = new PepperoniTopping(pizza);
        System.out.println(pizza.getDescription() + "\nCost: $" + pizza.getCost());

        pizza = new OlivesTopping(pizza);
        System.out.println(pizza.getDescription() + "\nCost: $" + pizza.getCost());

        // Creating a Thin Crust Pizza
        Pizza thinCrustPizza = new ThinCrustPizza();
        System.out.println(thinCrustPizza.getDescription() + "\nCost: $" + thinCrustPizza.getCost());

        thinCrustPizza = new PepperoniTopping(thinCrustPizza);
        System.out.println(thinCrustPizza.getDescription() + "\nCost: $" + thinCrustPizza.getCost());

        thinCrustPizza = new OlivesTopping(thinCrustPizza);
        System.out.println(thinCrustPizza.getDescription() + "\nCost: $" + thinCrustPizza.getCost());


    }
}

// in the above code, we have defined a `Pizza` interface (Component) that has two methods: `getDescription()` and `getCost()`. 
// We have two concrete implementations of the `Pizza` interface: `MargheritaPizza` (Concrete Component) and `ThinCrustPizza` (Concrete Component).
// We also have an abstract class `ToppingDecorator` (Decorator) that implements the `Pizza` interface (Component) and contains a reference to a `Pizza` object (Component).
// The `CheeseTopping` (Concrete Decorator), `PepperoniTopping` (Concrete Decorator), and `OlivesTopping` (Concrete Decorator) classes extend the `ToppingDecorator` (Decorator) class and add specific functionalities to the pizza.
// In the `DecoratorDemo` class, we create different types of pizzas and add various toppings to them dynamically, demonstrating the flexibility of the Decorator Design Pattern.

