import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;
import java.util.*;

public class HippodromeTest {

    @Test
    @DisplayName("Should throw IllegalArgumentException when horses list is null")
    void testConstructorNullHorses() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Should throw IllegalArgumentException when horses list is empty")
    void testConstructorEmptyHorsesList() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(Collections.emptyList()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("Should return the same list of horses passed to the constructor")
    void testGetHorsesReturnsSameList() {
        List<Horse> horseList = List.of(
                new Horse("Horse 1", 10.0),
                new Horse("Horse 2", 12.0),
                new Horse("Horse 3", 8.0)
        );
        Hippodrome hippodrome = new Hippodrome(horseList);

        List<Horse> returnedHorses = hippodrome.getHorses();
        // Check for equality of lists
        assertEquals(horseList, returnedHorses);
        // Check that these are different objects
        assertNotSame(horseList, returnedHorses);
    }

    @Test
    @DisplayName("Should call move method on all horses when move is called")
    void testMoveCallsMoveOnAllHorses() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            // Create mocks of horses
            mockHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for (Horse horse : mockHorses) {
            // Verify that the move method is called once on each horse
            verify(horse, times(1)).move();
        }
    }

    @Test
    @DisplayName("Should return the horse with the maximum distance as the winner")
    void testGetWinnerReturnsHorseWithMaxDistance() {
        Horse horse1 = new Horse("Horse 1", 10.0, 5.0);
        Horse horse2 = new Horse("Horse 2", 12.0, 8.0);
        Horse horse3 = new Horse("Horse 3", 11.0, 10.0);

        List<Horse> horseList = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horseList);

        Horse winner = hippodrome.getWinner();

        assertEquals(horse3, winner); // horse3 should be the winner
    }
}