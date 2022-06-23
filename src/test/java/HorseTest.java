import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    @Test
    void whenNameNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0.0, 0.0));
    }

    @Test
    void whenNameNullExceptionMsg() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 0.0, 0.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\u000C"})
    void whenNameEmptyString(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 0.0, 0.0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\u000C"})
    void whenNameEmptyStringExceptionMsg(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 0.0, 0.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void whenSpeedConstructParamNegativeException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("null", -1.0, 0.0));
    }

    @Test
    void whenSpeedConstructParamNegativeExceptionMsg() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("null", -1.0, 0.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void whenDistanceConstructParamNegativeException() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("null", 1.0, -1.0));
    }

    @Test
    void whenDistanceConstructParamNegativeExceptionMsg() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("null", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameTest() {
        String name = "Horse";
        Horse horse = new Horse(name, 0);
        assertEquals(name, horse.getName());
    }

    @Test
    void getSpeedTest() {
        String name = "Horse";
        double speed = 10.0;
        Horse horse = new Horse(name, speed);
        assertEquals(speed, horse.getSpeed());
    }

    @Test
    void getDistanceTest() {
        double distance = 10.0;
        Horse horse = new Horse("test", 0.0, distance);
        assertEquals(distance, horse.getDistance());
    }

    @Test
    void getDistanceWithConstructTwoParams() {
        Horse horse = new Horse("test", 0);
        assertEquals(0.0, horse.getDistance());
    }

    @Test
    @ExtendWith(MockitoExtension.class)
    void moveCallGetDoubleTest() {
        Horse horse = new Horse("test", 5.0, 5.0);
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {5.0,10.0,15.0})
    void moveTestWithFormula(Double number){
        double speedHorse = 10;
        double distanceHorse = 5;
        try(MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)){
            horseMockedStatic.when(()->Horse.getRandomDouble(0.2,0.9)).thenReturn(number);
            Horse horse = new Horse("Test",speedHorse,distanceHorse);
            horse.move();
            distanceHorse += speedHorse * number;
            assertEquals(distanceHorse,horse.getDistance());
        }
    }

}