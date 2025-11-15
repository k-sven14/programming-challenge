package de.bcxp.challenge;

import de.bcxp.challenge.model.WeatherDay;
import de.bcxp.challenge.service.WeatherAnalyzer;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class WeatherAnalyzerTest {
    private final WeatherAnalyzer analyzer = new WeatherAnalyzer();

    @Test
    void testReturnDayWithSmallestTemperatureSpread() {
        List<WeatherDay> days = List.of(
                new WeatherDay(1, 90, 70),
                new WeatherDay(2, 80, 60),
                new WeatherDay(3, 85, 84) // spread = 1 -> smallest
        );

        Optional<WeatherDay> result = analyzer.analyze(days);

        assertTrue(result.isPresent());
        assertEquals(3, result.get().getDay());
    }

    @Test
    void testHandleMultipleDaysWithSameSpread() {
        List<WeatherDay> days = List.of(
                new WeatherDay(1, 90, 80), // spread = 10
                new WeatherDay(2, 85, 75), // spread = 10
                new WeatherDay(3, 100, 50) // spread = 50
        );

        Optional<WeatherDay> result = analyzer.analyze(days);

        assertTrue(result.isPresent());
        assertEquals(1, result.get().getDay());
    }

    @Test
    void testReturnEmptyWhenListIsEmpty() {
        Optional<WeatherDay> result = analyzer.analyze(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void testReturnEmptyWhenListIsNull() {
        Optional<WeatherDay> result = analyzer.analyze(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testNegativeTemperatures() {
        List<WeatherDay> days = List.of(
                new WeatherDay(1, -5, -10), // spread = 5
                new WeatherDay(2, 0, -20),  // spread = 20
                new WeatherDay(3, 2, 1)     // spread = 1 -> smallest
        );

        Optional<WeatherDay> result = analyzer.analyze(days);

        assertTrue(result.isPresent());
        assertEquals(3, result.get().getDay());
    }

    @Test
    void testSingleElementList() {
        WeatherDay only = new WeatherDay(5, 30, 20);

        Optional<WeatherDay> result = analyzer.analyze(List.of(only));

        assertTrue(result.isPresent());
        assertEquals(5, result.get().getDay());
    }

    @Test
    void testZeroTemperatureSpread() {
        List<WeatherDay> days = List.of(
                new WeatherDay(1, 30, 25),
                new WeatherDay(2, 40, 40), // spread = 0
                new WeatherDay(3, 10, 5)
        );

        Optional<WeatherDay> result = analyzer.analyze(days);

        assertTrue(result.isPresent());
        assertEquals(2, result.get().getDay());
    }
}
