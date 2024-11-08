// Motorcycle.java
public class Motorcycle implements MotorVehicle {
    private String make;
    private String model;
    private int year;
    private int numWheels;
    private String motorcycleType;

    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public int getYear() {
        return year;
    }

    @Override
    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int getNumWheels() {
        return numWheels;
    }

    @Override
    public String getMotorcycleType() {
        return motorcycleType;
    }

    @Override
    public void setNumWheels(int numWheels) {
        this.numWheels = numWheels;
    }

    @Override
    public void setMotorcycleType(String motorcycleType) {
        this.motorcycleType = motorcycleType;
    }
}
