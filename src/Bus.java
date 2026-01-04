import java.time.Year;

public class Bus extends Vehicle implements Serviceable{
    private int passengerCapacity;
    private static final int SERVICE_INTERVAL_KM = 10000;

    public Bus(String model, int year, double basePrice, int passengerCapacity) {
        super(model, year, basePrice);
        setPassengerCapacity(passengerCapacity);
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        if (passengerCapacity < 10 || passengerCapacity > 100) {
            throw new IllegalArgumentException("Passenger capacity must be between 10 and 100");
        }
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public double calculateInsuranceFee() {
        int age = getAge(Year.now().getValue());
        double ageFactor = 1.0 + (age * 0.03);
        double capacityFactor = 1.0 + (passengerCapacity * 0.005);
        return getBasePrice() * 0.05 * ageFactor * capacityFactor;
    }

    @Override
    public void performService() {
        System.out.printf("Servicing bus: %s. Checking engine, transmission, seats for %d passengers.%n",
                getModel(), passengerCapacity);
    }

    @Override
    public int getServiceIntervalKm() {
        return SERVICE_INTERVAL_KM;
    }

    @Override
    public String toString() {
        return String.format("Bus [%s, passengerCapacity=%d, insuranceFee=%.2f]",
                super.toString(), passengerCapacity, calculateInsuranceFee());
    }
}
