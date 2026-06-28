package 4_BehavioralDPs;

// State Design Pattern is a behavioral design pattern that allows an object to change its behavior when its internal state changes.
// This pattern is useful for implementing finite state machines, where an object can be in one of several states and its behavior depends on the current state.

// In the State design pattern, we have the following components:
// 1. State: This is an interface that defines the common behavior for all concrete states.
// 2. Concrete States: These are classes that implement the State interface and provide specific implementations for the behavior associated with each state.
// 3. Context: This is a class that maintains a reference to the current state and delegates the behavior to the current state object.

// Example of State design pattern is a vending machine that can be in different states such as "No Coin", "Has Coin", and "Dispensing". 
// The behavior of the vending machine changes based on its current state.

// State interface
interface State {
    void insertCoin();
    void ejectCoin();
    void selectProduct();
    void dispenseProduct();
}

// Concrete State 1: No Coin State
class NoCoinState implements State {
    private final VendingMachine vendingMachine; // Reference to the context (VendingMachine)

    public NoCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin inserted.");
        vendingMachine.setState(vendingMachine.getHasCoinState());
    }

    @Override
    public void ejectCoin() {
        System.out.println("No coin to eject.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Insert coin first.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Insert coin first.");
    }
}

// Concrete State 2: Has Coin State
class HasCoinState implements State {
    private final VendingMachine vendingMachine;

    public HasCoinState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Coin already inserted.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Coin ejected.");
        vendingMachine.setState(vendingMachine.getNoCoinState());
    }

    @Override
    public void selectProduct() {
        System.out.println("Product selected.");
        vendingMachine.setState(vendingMachine.getDispensingState());
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Select product first.");
    }

    public VendingMachine getVendingMachine() {
        return vendingMachine;
    }
}

// Concrete State 3: Dispensing State
class DispensingState implements State {
    private final VendingMachine vendingMachine;

    public DispensingState(VendingMachine vendingMachine) {
        this.vendingMachine = vendingMachine;
    }

    @Override
    public void insertCoin() {
        System.out.println("Please wait, dispensing product.");
    }

    @Override
    public void ejectCoin() {
        System.out.println("Cannot eject coin, dispensing product.");
    }

    @Override
    public void selectProduct() {
        System.out.println("Already dispensing product.");
    }

    @Override
    public void dispenseProduct() {
        System.out.println("Product dispensed.");
        vendingMachine.setState(vendingMachine.getNoCoinState());
    }
}

// Context class: Vending Machine
// This class is responsible for maintaining the current state and delegating the behavior to the current state object.
class VendingMachine {
    // States of the vending machine (all possible states)
    private State noCoinState; // Reference to the No Coin State
    private State hasCoinState; // Reference to the Has Coin State
    private State dispensingState; // Reference to the Dispensing State

    private State currentState; // Reference to the current state of the vending machine

    public VendingMachine() {
        noCoinState = new NoCoinState(this);
        hasCoinState = new HasCoinState(this);
        dispensingState = new DispensingState(this);

        currentState = noCoinState; // Initial state
    }

    public void setState(State state) {
        currentState = state;
    }

    public State getNoCoinState() {
        return noCoinState;
    }

    public State getHasCoinState() {
        return hasCoinState;
    }

    public State getDispensingState() {
        return dispensingState;
    }

    // Methods to delegate behavior to the current state
    public void insertCoin() {
        currentState.insertCoin();
    }

    public void ejectCoin() {
        currentState.ejectCoin();
    }

    public void selectProduct() {
        currentState.selectProduct();
    }

    public void dispenseProduct() {
        currentState.dispenseProduct();
    }
}

// Demo class to test the State pattern implementation
public class StateDemo {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Test the vending machine behavior when a coin is inserted and a product is selected
        vendingMachine.insertCoin();
        vendingMachine.selectProduct();
        vendingMachine.dispenseProduct();

        System.out.println();

        // Test the vending machine behavior when no coin is inserted
        vendingMachine.selectProduct();
        vendingMachine.insertCoin();
        vendingMachine.ejectCoin();
        vendingMachine.selectProduct();
        vendingMachine.dispenseProduct();

    }
}

// In above code, we have implemented the State design pattern for a vending machine. The vending machine can be in three states: No Coin, Has Coin, and Dispensing. 
// Each state has its own behavior for inserting a coin, ejecting a coin, selecting a product, and dispensing a product. The VendingMachine class maintains the 
// current state and delegates the behavior to the current state object.
