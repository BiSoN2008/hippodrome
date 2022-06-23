import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void hippodromeConstructChkExceptionWithNullPrms() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void hippodromeConstructChkExceptionWithNullPrmsMsg() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void hippodromeConstructChkExceptionWithNullList() {
        List<Horse> list = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
    }

    @Test
    void hippodromeConstructChkExceptionWithNullListMsg() {
        List<Horse> list = new ArrayList<>();
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(list));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse("horse " + 1, i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertIterableEquals(horseList, hippodrome.getHorses());
    }

    @Test
    void moveTest() {
        List<Horse> horseList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horseList.add(Mockito.mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (int i = 0; i <horseList.size() ; i++) {
            Mockito.verify(horseList.get(i)).move();
        }
    }



    @Test
    void getWinnerTest() {
        List<Horse> horseList = new ArrayList<>();
        Horse horse1 = new Horse("horse 1", 2, 1);
        Horse horse2 = new Horse("horse 2", 2, 4);
        Horse horse3 = new Horse("horse 3", 2, 2);
        horseList.add(horse1);
        horseList.add(horse2);
        horseList.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horseList);
        Horse horseWinner = hippodrome.getWinner();
        assertEquals(horseWinner,horse2);
    }
}