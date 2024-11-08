// TruckVehicle.java
public interface TruckVehicle extends Vehicle {
    double getCargoCapacity();
    String getTransmissionType();
    void setCargoCapacity(double cargoCapacity);
    void setTransmissionType(String transmissionType);
}
