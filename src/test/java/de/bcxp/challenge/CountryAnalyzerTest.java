package de.bcxp.challenge;

import de.bcxp.challenge.model.Country;
import de.bcxp.challenge.service.CountryAnalyzer;
import org.junit.jupiter.api.Test;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class CountryAnalyzerTest {
    private final CountryAnalyzer analyzer = new CountryAnalyzer();

    @Test
    void testFindCountryWithHighestDensity() {
        List<Country> countries = List.of(
                new Country("A", 10_000_000, 100_000), // 100
                new Country("B", 20_000_000, 200_000), // 100
                new Country("C", 5_000_000, 10_000)    // 500
        );

        CountryAnalyzer analyzer = new CountryAnalyzer();
        Optional<Country> result = analyzer.analyze(countries);

        assertTrue(result.isPresent());
        assertEquals("C", result.get().getName());
        assertEquals(500, result.get().getPopulationDensity(), 0);
    }

    @Test
    void testReturnEmptyWhenListIsEmpty() {
        Optional<Country> result = analyzer.analyze(List.of());
        assertTrue(result.isEmpty());
    }

    @Test
    void testReturnEmptyWhenListIsNull() {
        Optional<Country> result = analyzer.analyze(null);
        assertTrue(result.isEmpty());
    }

    @Test
    void testHandleCountriesWithZeroArea() {
        List<Country> countries = List.of(
                new Country("USA", 1_000_000, 0),
                new Country("Canada", 10_000, 10)
        );

        Optional<Country> result = analyzer.analyze(countries);
        assertTrue(result.isPresent());
        assertEquals("Canada", result.get().getName());
    }

    @Test
    void testHandleCountriesWithZeroPopulation() {
        List<Country> countries = List.of(
                new Country("Norway", 0, 1000),
                new Country("Sweden", 1000, 100)
        );

        Optional<Country> result = analyzer.analyze(countries);
        assertTrue(result.isPresent());
        assertEquals("Sweden", result.get().getName());
    }

    @Test
    void testTieOnPopulationDensity() {
        List<Country> countries = List.of(
                new Country("Germany", 10_000_000, 100_000), // 100
                new Country("Italia",  5_000_000,  50_000)  // 100
        );

        Optional<Country> result = analyzer.analyze(countries);

        assertTrue(result.isPresent());
        assertEquals("Germany", result.get().getName());
    }

    @Test
    void testNegativeValues() {
        List<Country> countries = List.of(
                new Country("BadCountry", -1000, 100),  // -10
                new Country("NormalCountry", 1000, 50)  // 20
        );

        Optional<Country> result = analyzer.analyze(countries);

        assertTrue(result.isPresent());
        assertEquals("NormalCountry", result.get().getName());
    }

    @Test
    void testSingleElementList() {
        Country only = new Country("Solo", 1000, 100);

        Optional<Country> result = analyzer.analyze(List.of(only));

        assertTrue(result.isPresent());
        assertEquals("Solo", result.get().getName());
    }
}
