import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VehicleTest {

    @Test
    void testConstructorWithValidDailyRate() {
        Vehicle vehicle = new Vehicle("Sedan", 75.00);
        assertEquals("Sedan", vehicle.getModel());
        assertEquals(75.00, vehicle.getDailyRate(), 0.001);
    }

    @Test
    void testConstructorWithInvalidDailyRate() {
        assertThrows(IllegalArgumentException.class, () -> new Vehicle("SUV", -10.00));
    }

    @Test
    void testCalculateRentalPriceWithoutDiscount() {
        Vehicle vehicle = new Vehicle("Compact", 50.00);
        assertEquals(150.00, vehicle.calculateRentalPrice(3), 0.001);
    }

    @Test
    void testCalculateRentalPriceWithDiscount() {
        Vehicle vehicle = new Vehicle("Mid-size", 100.00);
        assertEquals(630.00, vehicle.calculateRentalPrice(7), 0.001); // 7 days * 100 * (1 - 0.1)
    }

    @Test
    void testCalculateRentalPriceWithNegativeDays() {
        Vehicle vehicle = new Vehicle("Truck", 120.00);
        assertThrows(IllegalArgumentException.class, () -> vehicle.calculateRentalPrice(-2));
    }

    @Test
    void testCalculateLateReturnFee() {
        Vehicle vehicle = new Vehicle("Motorcycle", 30.00);
        assertEquals(1.80, vehicle.calculateLateReturnFee(3), 0.001); // 30 * 0.02 * 3
    }

    @Test
    void testCalculateLateReturnFeeWithNegativeDays() {
        Vehicle vehicle = new Vehicle("Bus", 200.00);
        assertThrows(IllegalArgumentException.class, () -> vehicle.calculateLateReturnFee(-1));
    }

    @Test
    void testToString() {
        Vehicle vehicle = new Vehicle("Van", 90.50);
        assertEquals("Model: Van | Daily Rate: R$90.50", vehicle.toString());
    }
}
