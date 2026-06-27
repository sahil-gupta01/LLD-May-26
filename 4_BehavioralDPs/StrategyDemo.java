package 4_BehavioralDPs;

// Strategy Design Pattern is a behavioral design pattern that allows you to 
// define a family of algorithms, encapsulate each one, and make them interchangeable. 
// The Strategy pattern lets the algorithm vary independently from clients that use it.

// In the Strategy design pattern, we have the following components:
// 1. Strategy: This is an interface that defines a common method for all supported algorithms.
// 2. Concrete Strategies: These are classes that implement the Strategy interface and provide specific implementations of the algorithm.

// Example of Strategy design pattern is a payment processing system that supports multiple payment methods 
// such as credit card, PayPal, and Bitcoin. Each payment method can be implemented as a separate strategy, allowing the system to switch between them easily.

// Strategy interface
interface PaymentStrategy {
    void pay(double amount);
}

// Concrete Strategy 1: Credit Card Payment
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;
    private String cvv;

    public CreditCardPayment(String cardNumber, String cardHolderName, String cvv) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cvv = cvv;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using Credit Card.");
    }
}

// Concrete Strategy 2: PayPal Payment
class PayPalPayment implements PaymentStrategy {
    private String email;
    private String password;

    public PayPalPayment(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " using PayPal.");
    }
}


// Context class that uses the strategy
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}


public class StrategyDemo {

    public static void main(String[] args) {
        // Pay using Credit Card
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9876-5432", "John Doe", "123");
        PaymentContext paymentContext1 = new PaymentContext(creditCardPayment);
        paymentContext1.executePayment(100.0);

        // Pay using PayPal
        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com", "password");
        PaymentContext paymentContext2 = new PaymentContext(payPalPayment);
        paymentContext2.executePayment(200.0);
    }
}

// In this example, we have defined a PaymentStrategy interface that declares a pay method.
// We have two concrete implementations of the PaymentStrategy interface: CreditCardPayment and PayPalPayment.
// The PaymentContext class uses a PaymentStrategy to execute the payment.
// This allows the client to choose the payment method at runtime, demonstrating the flexibility and reusability of the Strategy Design Pattern.