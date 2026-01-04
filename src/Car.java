import java.time.Year;

public class Car extends Vehicle implements Serviceable {
    private int numberOfDoors;
    private static final int SERVICE_INTERVAL_KM = 15000;

    public Car(String model, int year, double basePrice, int numberOfDoors) {
        super(model, year, basePrice);
        setNumberOfDoors(numberOfDoors);
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors < 2 || numberOfDoors > 6) {
            throw new IllegalArgumentException("Number of doors must be between 2 and 6");
        }
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public double calculateInsuranceFee() {
        int age = getAge(Year.now().getValue());
        double ageFactor = 1.0 + (age * 0.05);
        double doorsFactor = 1.0 + (numberOfDoors * 0.02);
        return getBasePrice() * 0.03 * ageFactor * doorsFactor;
    }

    @Override
    public void performService() {
        System.out.printf("Servicing car: %s. Checking engine, oil, brakes, and %d doors.%n",
                getModel(), numberOfDoors);
    }

    @Override
    public int getServiceIntervalKm() {
        return SERVICE_INTERVAL_KM;
    }

    @Override
    public String toString() {
        return String.format("Car [%s, numberOfDoors=%d, insuranceFee=%.2f]",
                super.toString(), numberOfDoors, calculateInsuranceFee());
    }
}
