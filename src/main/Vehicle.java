import java.text.NumberFormat;
import java.util.Locale;

interface CostCalculator {
    double calculate(int days);
}

public class Vehicle {
    private final String model;
    private final double dailyRate;
    private static final int DISCOUNT_THRESHOLD_DAYS = 7;
    private static final double REGULAR_DISCOUNT_RATE = 0.1;
    private static final double LATE_FEE_FACTOR = 0.02;

    public Vehicle(String model, double dailyRate) {
        if (dailyRate <= 0) {
            throw new IllegalArgumentException("The daily rate must be a positive value.");
        }
        this.model = model;
        this.dailyRate = dailyRate;
    }

    public String getModel() {
        return model;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    @Override
    public String toString() {
        return String.format("Model: %s | Daily Rate: R$%.2f", model, dailyRate);
    }

    public double calculateRentalPrice(int rentalDays) {
        if (rentalDays < 0) {
            throw new IllegalArgumentException("Rental duration cannot be negative.");
        }
        double basePrice = rentalDays * dailyRate;
        return (rentalDays >= DISCOUNT_THRESHOLD_DAYS) ? basePrice * (1 - REGULAR_DISCOUNT_RATE) : basePrice;
    }

    public double calculateLateReturnFee(int daysLate) {
        if (daysLate < 0) {
            throw new IllegalArgumentException("Late days cannot be negative.");
        }
        return dailyRate * LATE_FEE_FACTOR * daysLate;
    }
}
