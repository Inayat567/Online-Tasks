// VehicleInformationSystem.java
import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleInformationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User interaction to create and display vehicle objects
        try {
            int option;
            do {
                displayMenu();
                try {
                    option = scanner.nextInt();
                    scanner.nextLine(); // consume the newline character

                    switch (option) {
                        case 1:
                            System.out.println("\t\t$$$$$$$$$$**************  Enter details of Car:  **************$$$$$$$$$$");
                            Car car = new Car();
                            inputVehicleDetails(scanner, car);
                            inputCarDetails(scanner, car);
                            displayVehicleDetails(car);
                            break;
                        case 2:
                            System.out.println("\t\t$$$$$$$$$$**************  Enter details of Motorcycle:  **************$$$$$$$$$$");
                            Motorcycle motorcycle = new Motorcycle();
                            inputVehicleDetails(scanner, motorcycle);
                            inputMotorcycleDetails(scanner, motorcycle);
                            displayVehicleDetails(motorcycle);
                            break;
                        case 3:
                            System.out.println("\t\t$$$$$$$$$$**************  Enter details of Truck:  **************$$$$$$$$$$");
                            Truck truck = new Truck();
                            inputVehicleDetails(scanner, truck);
                            inputTruckDetails(scanner, truck);
                            displayVehicleDetails(truck);
                            break;
                        case 0:
                            System.out.println("\t\t$$$$$$$$$$**************  Exiting the program. Goodbye!  **************$$$$$$$$$$");
                            break;
                        default:
                            System.out.println("\n\t\t$$$$$$$$$$**************  Invalid option. Please enter a valid option  **************$$$$$$$$$$");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("\n\t\tInvalid input. Please enter a valid option   **************$$$$$$$$$$");
                    scanner.nextLine(); // consume the invalid input
                    option = -1; // set option to -1 to continue the loop
                }
            } while (option != 0);
        } finally {
            scanner.close();
        }
    }

    private static void displayMenu() {
        System.out.println("\n\n\t\t$$$$$$$$$$**************  Vehicle Information System Menu:     **************$$$$$$$$$$");
        System.out.println("\t\t$$$$$$$$$$**************  1. Enter details of Car              **************$$$$$$$$$$");
        System.out.println("\t\t$$$$$$$$$$**************  2. Enter details of Motorcycle       **************$$$$$$$$$$");
        System.out.println("\t\t$$$$$$$$$$**************  3. Enter details of Truck            **************$$$$$$$$$$");
        System.out.println("\t\t$$$$$$$$$$**************  0. Exit                              **************$$$$$$$$$$");
        System.out.print("\t\t$$$$$$$$$$**************  Choose an option:   ");
    }

    private static void inputVehicleDetails(Scanner scanner, Vehicle vehicle) {
        System.out.print("\t\tEnter make: ");
        vehicle.setMake(scanner.nextLine());

        System.out.print("\t\tEnter model: ");
        vehicle.setModel(scanner.nextLine());

        System.out.print("\t\tEnter year: ");
        vehicle.setYear(scanner.nextInt());
        scanner.nextLine(); // consume the newline character
    }

    private static void inputCarDetails(Scanner scanner, CarVehicle car) {
        System.out.print("\t\tEnter number of doors: ");
        car.setNumDoors(scanner.nextInt());
        scanner.nextLine(); // consume the newline character

        System.out.print("\t\tEnter fuel type: ");
        car.setFuelType(scanner.nextLine());
    }

    private static void inputMotorcycleDetails(Scanner scanner, MotorVehicle motorcycle) {
        System.out.print("\t\tEnter number of wheels: ");
        motorcycle.setNumWheels(scanner.nextInt());
        scanner.nextLine(); // consume the newline character

        System.out.print("\t\tEnter motorcycle type: ");
        motorcycle.setMotorcycleType(scanner.nextLine());
    }

    private static void inputTruckDetails(Scanner scanner, TruckVehicle truck) {
        System.out.print("\t\tEnter cargo capacity (in tons): ");
        truck.setCargoCapacity(scanner.nextDouble());
        scanner.nextLine(); // consume the newline character

        System.out.print("\t\tEnter transmission type: ");
        truck.setTransmissionType(scanner.nextLine());
    }

    private static void displayVehicleDetails(Vehicle vehicle) {
        System.out.println("\n\n\t\t\t\t$$$$$$$$$$************** Vehicle Details:   **************$$$$$$$$$$");
        System.out.println("\t\t\t\t Make: " + vehicle.getMake());
        System.out.println("\t\t\t\t Model: " + vehicle.getModel());
        System.out.println("\t\t\t\t Year: " + vehicle.getYear());
    }
}
