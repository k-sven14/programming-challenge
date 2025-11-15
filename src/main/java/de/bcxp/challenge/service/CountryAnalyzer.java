package de.bcxp.challenge.service;

import de.bcxp.challenge.api.Analyzer;
import de.bcxp.challenge.model.Country;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Analyzer implementation for determining the country with the highest
 * population density.
 *
 * <p>This class encapsulates the evaluation logic, keeping analysis concerns
 * separate from data loading and representation, which supports clean
 * architecture and future extensibility.</p>
 */
public class CountryAnalyzer implements Analyzer<Country> {

    /**
     * Finds the country with the highest population density.
     *
     * <p>Returns {@link Optional#empty()} when the input list is null or empty,
     * ensuring safe and predictable behaviour for edge cases.</p>
     *
     * @param data list of {@link Country} objects
     * @return an {@link Optional} containing the country with the highest
     *         population density, or empty if no valid data exists
     */
    @Override
    public Optional<Country> analyze(List<Country> data) {
        if (data == null || data.isEmpty()) {
            return Optional.empty();
        }

        return data.stream()
                .max(Comparator.comparingDouble(Country::getPopulationDensity));
    }
}
