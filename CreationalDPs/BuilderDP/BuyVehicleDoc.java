package BuilderDP;

// Builder design pattern is a creational design pattern that allows for the step-by-step construction of complex objects. 
// We cannot create the object of the class directly using the constructor, instead we use a separate builder class that provides methods to set the properties of the object and a build() method to create the object.
// The Builder pattern is particularly useful when an object needs to be created with many optional parameters or when the construction process involves multiple steps and validations.

class Vehicle {
    private String make;
    private String model;
    private int year;
    private String color;
    private String engineType;

    private Vehicle(VehicleBuilder builder) {
        this.make = builder.make;
        this.model = builder.model;
        this.year = builder.year;
        this.color = builder.color;
        this.engineType = builder.engineType;
    }

    public static VehicleBuilder builder() {
        return new VehicleBuilder();
    }

    public static class VehicleBuilder {
        private String make;
        private String model;
        private int year;
        private String color;
        private String engineType;

        public VehicleBuilder() {
            // Default constructor
        }

        public VehicleBuilder setMake(String make) {
            this.make = make;
            return this;
        }

        public VehicleBuilder setModel(String model) {
            this.model = model;
            return this;
        }

        public VehicleBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public VehicleBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public VehicleBuilder setEngineType(String engineType) {
            this.engineType = engineType;
            return this;
        }

        public Vehicle build() {
            
            validate(); // Validate the properties before creating the Vehicle object
            return new Vehicle(this);
        }

        private void validate() {
            // Additional validation logic can be implemented here if needed
            // Here we can add validation logic before creating the Vehicle object if needed
            if (this.make == null || this.model == null) {
                throw new IllegalStateException("Make and Model are required fields");
            }
            if (this.year < 1886) { // The first car was invented in 1886
                throw new IllegalStateException("Year must be 1886 or later");
            }
            if (this.engineType == null) {
                throw new IllegalStateException("Engine Type is a required field");
            }

        }
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", engineType='" + engineType + '\'' +
                '}';
    }
}

public class BuyVehicleDoc {
    public static void main(String[] args) {
        Vehicle vehicle = Vehicle.builder()
                .setMake("Toyota")
                .setModel("Camry")
                .setYear(2020)
                .setColor("Red")
                .setEngineType("V6")
                .build();

        System.out.println(vehicle);
    }

}