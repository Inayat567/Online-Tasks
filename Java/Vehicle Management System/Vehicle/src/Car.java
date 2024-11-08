// Car.java
public class Car implements CarVehicle {
    private String make;
    private String model;
    private int year;
    private int numDoors;
    private String fuelType;

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
    public int getNumDoors() {
        return numDoors;
    }

    @Override
    public String getFuelType() {
        return fuelType;
    }

    @Override
    public void setNumDoors(int numDoors) {
        this.numDoors = numDoors;
    }

    @Override
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
