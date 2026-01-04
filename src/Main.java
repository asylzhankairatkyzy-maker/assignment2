public class Main {
    public static void main(String[] args) {
        System.out.println("Fleet Management System");

        Serviceable serviceableVehicle = new Car("Test Car", 2023, 20000, 4);
        serviceableVehicle.performService();

        FleetApp app = new FleetApp();
        app.run();
    }
}