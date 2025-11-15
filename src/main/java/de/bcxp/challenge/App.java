package de.bcxp.challenge;

import de.bcxp.challenge.model.Country;
import de.bcxp.challenge.model.WeatherDay;
import de.bcxp.challenge.reader.CsvCountryReader;
import de.bcxp.challenge.reader.CsvWeatherReader;
import de.bcxp.challenge.service.CountryAnalyzer;
import de.bcxp.challenge.service.WeatherAnalyzer;

import java.util.List;
import java.util.Optional;

/**
 * The entry class for your solution. This class is only aimed as starting point and not intended as baseline for your software
 * design. Read: create your own classes and packages as appropriate.
 */
public final class App {

    /**
     * This is the main entry method of your program.
     * @param args The CLI arguments passed
     */
    public static void main(String... args) {

        runWeatherAnalysis("src/main/resources/de/bcxp/challenge/weather.csv");
        runCountryAnalysis("src/main/resources/de/bcxp/challenge/countries.csv");
    }

    private static void runWeatherAnalysis(String filePath) {
        CsvWeatherReader weatherReader = new CsvWeatherReader();
        List<WeatherDay> weatherDays = weatherReader.readData(filePath);

        WeatherAnalyzer weatherAnalyzer = new WeatherAnalyzer();
        Optional<WeatherDay> result = weatherAnalyzer.analyze(weatherDays);

        result.ifPresentOrElse(
                day -> System.out.printf("Day with smallest temperature spread: %d (Δ=%.1f)%n",
                        day.getDay(), day.getTemperatureDifference()),
                () -> System.out.println("No weather data found.")
        );
    }

    private static void runCountryAnalysis(String filePath) {
        CsvCountryReader countryReader = new CsvCountryReader();
        List<Country> countries = countryReader.readData(filePath);

        CountryAnalyzer analyzer = new CountryAnalyzer();
        Optional<Country> result = analyzer.analyze(countries);

        result.ifPresentOrElse(
                c -> System.out.printf("Country with highest population density: %s (%.2f population/km²)%n",
                        c.getName(), c.getPopulationDensity()),
                () -> System.out.println("No country data found.")
        );
    }
}
