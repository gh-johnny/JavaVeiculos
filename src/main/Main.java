import java.text.NumberFormat;
import java.util.Locale;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) {
        RentalUnit myRide = new RentalUnit("Hatch", 60.00);

        showVehicleDetails(myRide);
        runCostAnalysis(myRide);
    }

    private static void showVehicleDetails(RentalUnit unit) {
        System.out.println("Vehicle Snapshot:");
        System.out.println(unit.toString());
        System.out.println("---------");
    }

    private static void runCostAnalysis(RentalUnit unit) {
        NumberFormat brCurrency = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("Lease Cost Breakdown:");
        displayCost("Short-Term Lease (5 days)", unit, days -> unit.calculateLeaseCost(days), 5, brCurrency);
        displayCost("Extended Lease (10 days)", unit, days -> unit.calculateLeaseCost(days), 10, brCurrency);
        displayCost("Late Return (3 days)", unit, days -> unit.calculateOverdueCharge(days), 3, brCurrency);
    }

    private static void displayCost(String label, RentalUnit unit, Function<Integer, Double> calculator, int days, NumberFormat formatter) {
        System.out.printf("%s: %s%n", label, formatter.format(calculator.apply(days)));
    }
}
