import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FleetApp {
    private List<Vehicle> vehicles;
    private Scanner scanner;

    public FleetApp() {
        vehicles = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readIntInput("Choose an option: ");

            switch (choice) {
                case 1 -> printAllVehicles();
                case 2 -> addNewCar();
                case 3 -> addNewBus();
                case 4 -> showTotalInsuranceFees();
                case 5 -> showVehiclesOlderThan();
                case 6 -> performServiceForAll();
                case 7 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n Fleet Management System");
        System.out.println("1. Print all vehicles");
        System.out.println("2. Add new car");
        System.out.println("3. Add new bus");
        System.out.println("4. Show total yearly insurance fees");
        System.out.println("5. Show vehicles older than N years");
        System.out.println("6. Perform service for all vehicles");
        System.out.println("7. Quit");
    }

    private void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    private void addNewCar() {
        System.out.println("Enter car details:");
        String model = readStringInput("Model: ");
        int year = readIntInput("Year: ");
        double basePrice = readDoubleInput("Base price: ");
        int doors = readIntInput("Number of doors (2-6): ");

        try {
            Car car = new Car(model, year, basePrice, doors);
            vehicles.add(car);
            System.out.println("Car added successfully. ID: " + car.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void addNewBus() {
        System.out.println("Enter bus details:");
        String model = readStringInput("Model: ");
        int year = readIntInput("Year: ");
        double basePrice = readDoubleInput("Base price: ");
        int capacity = readIntInput("Passenger capacity (10-100): ");

        try {
            Bus bus = new Bus(model, year, basePrice, capacity);
            vehicles.add(bus);
            System.out.println("Bus added successfully. ID: " + bus.getId());
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void showTotalInsuranceFees() {
        double total = 0;
        for (Vehicle vehicle : vehicles) {
            total += vehicle.calculateInsuranceFee();
        }
        System.out.printf("Total yearly insurance fees: %.2f%n", total);
    }

    private void showVehiclesOlderThan() {
        int currentYear = readIntInput("Enter current year: ");
        int n = readIntInput("Enter N (minimum age in years): ");

        System.out.println("Vehicles older than " + n + " years:");
        boolean found = false;
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getAge(currentYear) > n) {
                System.out.println(vehicle);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No vehicles found.");
        }
    }

    private void performServiceForAll() {
        System.out.println("Performing service for all vehicles:");

        for (Vehicle vehicle : vehicles) {
            if (vehicle instanceof Serviceable) {
                Serviceable serviceable = (Serviceable) vehicle;
                serviceable.performService();
                System.out.println("  Next service in " + serviceable.getServiceIntervalKm() + " km");
            }
        }

        Serviceable s1 = new Car("Toyota Camry", 2022, 25000, 4);
        Serviceable s2 = new Bus("Mercedes Sprinter", 2020, 50000, 20);

        s1.performService();
        s2.performService();
    }

    private String readStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private int readIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
