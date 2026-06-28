package 4_BehavioralDPs;

import java.util.List;
import java.util.ArrayList;
// Observer Design Pattern is a behavioral design pattern that allows an object (the subject) to notify a list of dependent objects (observers) 
// automatically when its state changes. This pattern is useful for implementing distributed event handling systems, where multiple objects need to be 
// informed about changes in another object.

// In the Observer design pattern, we have the following components:
// 1. Subject: This is the object that maintains a list of observers and notifies them of any state changes. It provides methods to attach, detach, and notify observers.
// 2. Observer: This is an interface that defines the update method, which is called by the subject to notify the observer of any state changes.
// 3. Concrete Subject: This is a class that implements the Subject interface and maintains the state of the subject. It notifies the observers when its state changes.

// Example of Observer design pattern is a weather monitoring system where multiple display devices (observers) need to be updated whenever the weather data (subject) changes.

// Subject interface
interface Subject {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Observer interface
interface Observer {
    void update(float temperature, float humidity, float pressure);
}

// Concrete Observer 1: Current Conditions Display
class CurrentConditionsDisplay implements Observer {
    private float temperature;
    private float humidity;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        display();
    }

    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
}

// Concrete Observer 2: Statistics Display
class StatisticsDisplay implements Observer {
    private final List<Float> temperatureReadings = new ArrayList<>();

    @Override
    public void update(float temperature, float humidity, float pressure) {
        temperatureReadings.add(temperature);
        display();
    }

    public void display() {
        float sum = 0;
        for (float temp : temperatureReadings) {
            sum += temp;
        }
        float average = sum / temperatureReadings.size();
        System.out.println("Average temperature: " + average + "F degrees");
    }
}

// Concrete Observer 3: Forecast Display
class ForecastDisplay implements Observer {
    private float lastPressure;
    private float currentPressure;

    @Override
    public void update(float temperature, float humidity, float pressure) {
        lastPressure = currentPressure;
        currentPressure = pressure;
        display();
    }

    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same.");
        } else {
            System.out.println("Watch out for cooler, rainy weather.");
        }
    }
}

// Concrete Subject: Weather Data
class WeatherData implements Subject {
    private final List<Observer> observers;
    private float temperature;
    private float humidity;
    private float pressure;

    public WeatherData() {
        observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(temperature, humidity, pressure);
        }
    }

    public void measurementsChanged() {
        notifyObservers();
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }
}   

public class ObserverDemo {
    public static void main(String[] args) {
        WeatherData weatherData = new WeatherData();

        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        weatherData.registerObserver(currentDisplay);
        weatherData.registerObserver(statisticsDisplay);
        weatherData.registerObserver(forecastDisplay);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}

// in this example, we have a WeatherData class that acts as the subject and maintains a list of observers. The CurrentConditionsDisplay, StatisticsDisplay, and ForecastDisplay classes 
// act as concrete observers that implement the Observer interface. When the weather data changes, the WeatherData class notifies all registered observers by calling their update method, 
// which allows them to display the updated information.