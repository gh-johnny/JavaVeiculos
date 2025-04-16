import java.text.NumberFormat;
import java.util.Locale;

class Vehicle {
    private String tipo;
    private double valorDiaria;

    public Vehicle(String tipo, double valorDiaria) {
        this.tipo = tipo;
        this.valorDiaria = valorDiaria;
    }

    public String getDetails() {
        return String.format("Tipo: %s, Valor da Diária: %s", tipo, formatCurrency(valorDiaria));
    }

    public double calculateRentalCost(int dias) {
        return valorDiaria * dias;
    }

    public double calculateLongTermRental(int dias) {
        double desconto = dias > 7 ? 0.10 : 0; // 10% de desconto para mais de 7 dias
        return valorDiaria * dias * (1 - desconto);
    }

    public double calculateLateFee(int diasAtraso) {
        return valorDiaria * 1.5 * diasAtraso; // Multa de 1.5 vezes o valor da diária por dia de atraso
    }

    private String formatCurrency(double valor) {
        NumberFormat moeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return moeda.format(valor);
    }
}

public class RentalAnalysis {
    public static void main(String[] args) {
        Vehicle myCar = new Vehicle("Hatch", 60.00);

        displayVehicleInfo(myCar);
        displayRentalCosts(myCar);
    }

    private static void displayVehicleInfo(Vehicle vehicle) {
        System.out.println("Vehicle Information:");
        System.out.println(vehicle.getDetails());
        System.out.println("-------------------");
    }

    private static void displayRentalCosts(Vehicle vehicle) {
        System.out.println("Cost Analysis:");
        System.out.println("5-day Rental: " + vehicle.formatCurrency(vehicle.calculateRentalCost(5)));
        System.out.println("10-day Rental (progressive discount): " +
                vehicle.formatCurrency(vehicle.calculateLongTermRental(10)));
        System.out.println("Late Fee (3 days): " +
                vehicle.formatCurrency(vehicle.calculateLateFee(3)));
    }
}
