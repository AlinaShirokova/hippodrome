import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

public class HorseTest {

    @Test
    @DisplayName("Should throw IllegalArgumentException when name is null")
    void testConstructorNameNull() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 10.0, 0.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", " \n"})
    @DisplayName("Should throw IllegalArgumentException when name is blank or empty")
    void testConstructorNameBlank(String name) {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 10.0, 0.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when speed is negative")
    void testConstructorSpeedNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", -1.0, 0.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when distance is negative")
    void testConstructorDistanceNegative() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", 10.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Should return name passed to the constructor")
    void testGetName() {
        Horse horse = new Horse("Black Beauty", 10.0, 5.0);
        assertEquals("Black Beauty", horse.getName());
    }

    @Test
    @DisplayName("Should return speed passed to the constructor")
    void testGetSpeed() {
        Horse horse = new Horse("Black Beauty", 10.0, 5.0);
        assertEquals(10.0, horse.getSpeed());
    }

    @Test
    @DisplayName("Should return distance passed to the constructor")
    void testGetDistance() {
        Horse horse = new Horse("Black Beauty", 10.0, 5.0);
        assertEquals(5.0, horse.getDistance());
    }

    @Test
    @DisplayName("Should return zero distance when created with two parameters")
    void testGetDistanceWithTwoParams() {
        Horse horse = new Horse("Black Beauty", 10.0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    @DisplayName("Should call getRandomDouble when move is called")
    void testMoveCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Black Beauty", 10.0, 5.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
            assertEquals(10.0, horse.getDistance()); // distance should be updated according to 5 + 10 * 0.5
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    @DisplayName("Should update distance correctly on move with different getRandomDouble values")
    void testMoveUpdatesDistance(double mockReturnValue) {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("Black Beauty", 10.0, 5.0);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(mockReturnValue);
            horse.move();
            double expectedDistance = 5.0 + 10.0 * mockReturnValue;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}
