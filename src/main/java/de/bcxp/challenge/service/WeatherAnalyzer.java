package de.bcxp.challenge.service;

import de.bcxp.challenge.api.Analyzer;
import de.bcxp.challenge.model.WeatherDay;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Analyzer implementation for identifying the day with the smallest
 * temperature spread (difference between max and min temperature).
 *
 * <p>This class contains only the analysis logic, keeping it decoupled from
 * file I/O and data models, which supports clean architecture, easier testing,
 * and future extensibility.</p>
 */
public class WeatherAnalyzer implements Analyzer<WeatherDay> {

    /**
     * Finds the day with the smallest temperature difference.
     *
     * <p>Returns {@link Optional#empty()} if the input list is null or empty.
     * This ensures robust handling of edge cases and prevents callers from
     * dealing with null values.</p>
     *
     * @param data list of {@link WeatherDay} entries
     * @return an {@link Optional} containing the day with the smallest
     *         temperature spread, or empty if no data is available
     */
    @Override
    public Optional<WeatherDay> analyze(List<WeatherDay> data) {
        if (data == null || data.isEmpty()) {
            return Optional.empty();
        }

        return data.stream()
                .min(Comparator.comparingDouble(WeatherDay::getTemperatureDifference));
    }
}
