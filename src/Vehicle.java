import java.time.Year;

public abstract class Vehicle {
    private int id;
    private static int idGen = 1;
    private String model;
    private int year;
    private double basePrice;

    public Vehicle(String model, int year, double basePrice) {
        this.id = idGen++;
        setModel(model);
        setYear(year);
        setBasePrice(basePrice);
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("error");
        }
        this.model = model;
    }

    public void setYear(int year) {
        int currentYear = Year.now().getValue();
        if (year < 1886 || year > currentYear) { // 1886 - первый автомобиль
            throw new IllegalArgumentException("Year must be between 1886 and " + currentYear);
        }
        this.year = year;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("error" +
                    "");
        }
        this.basePrice = basePrice;
    }

    public int getAge(int currentYear) {
        return currentYear - year;
    }

    public abstract double calculateInsuranceFee();

    @Override
    public String toString() {
        return String.format("Vehicle [id=%d, model=%s, year=%d, basePrice=%.2f]",
                id, model, year, basePrice);
    }

}
