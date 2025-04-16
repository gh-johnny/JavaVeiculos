import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Vehicle myCar = new Vehicle("Hatch", 60.00);

        displayVehicleInfo(myCar);
        analyzeRentalScenarios(myCar);
    }

    private static void displayVehicleInfo(Vehicle vehicle) {
        System.out.println("Vehicle Specs:");
        System.out.println(vehicle.toString());
        System.out.println("---------");
    }

    private static void analyzeRentalScenarios(Vehicle vehicle) {
        NumberFormat brlFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("Rental Cost Projections:");
        showProjection("Standard Hire (5 days)", vehicle::calculateRentalPrice, 5, brlFormat);
        showProjection("Extended Hire (10 days)", vehicle::calculateRentalPrice, 10, brlFormat);
        showProjection("Late Return Fee (3 days)", vehicle::calculateLateReturnFee, 3, brlFormat);
    }

    private static void showProjection(String scenario, CostCalculator calculator, int days, NumberFormat formatter) {
        System.out.printf("%s: %s%n", scenario, formatter.format(calculator.calculate(days)));
    }
}
