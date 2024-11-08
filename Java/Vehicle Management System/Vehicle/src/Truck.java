// Truck.java
public class Truck implements TruckVehicle {
    private String make;
    private String model;
    private int year;
    private double cargoCapacity;
    private String transmissionType;

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
    public double getCargoCapacity() {
        return cargoCapacity;
    }

    @Override
    public String getTransmissionType() {
        return transmissionType;
    }

    @Override
    public void setCargoCapacity(double cargoCapacity) {
        this.cargoCapacity = cargoCapacity;
    }

    @Override
    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }
}
